public class Philosopher_Deadlock extends Philosopher {

    public Philosopher_Deadlock(int id, Fork leftFork, Fork rightFork) {
        super(id, leftFork, rightFork);
    }

    @Override
    public void execute() {
        while (!Thread.currentThread().isInterrupted()) {
            think();
            takeFork(leftFork);
            takeFork(rightFork);
            eat();
            putFork(rightFork);
            putFork(leftFork);
        }
    }
}
