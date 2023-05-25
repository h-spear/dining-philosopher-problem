public class Philosopher_CircularWaitPrevention extends Philosopher {

    public Philosopher_CircularWaitPrevention(int id, Fork leftFork, Fork rightFork) {
        super(id, leftFork, rightFork);
    }

    @Override
    protected void execute() {
        while (!Thread.currentThread().isInterrupted()) {
            think();
            takeForks();
            eat();
            putForks();
        }
    }

    private void takeForks() {
        if ((id & 1) == 0) {
            takeFork(leftFork);
            takeFork(rightFork);
        } else {
            takeFork(rightFork);
            takeFork(leftFork);
        }
    }

    private void putForks() {
        if ((id & 1) == 0) {
            putFork(rightFork);
            putFork(leftFork);
        } else {
            putFork(leftFork);
            putFork(rightFork);
        }
    }
}
