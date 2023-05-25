public class Philosopher_Synchronized extends Philosopher {

    public Philosopher_Synchronized(int id, Fork leftFork, Fork rightFork) {
        super(id, leftFork, rightFork);
    }

    @Override
    protected void execute() {
        while (!Thread.currentThread().isInterrupted()) {
            execute(this);
        }
    }

    private static synchronized void execute(Philosopher philosopher) {
        philosopher.think();
        philosopher.takeFork(philosopher.leftFork);
        philosopher.takeFork(philosopher.rightFork);
        philosopher.eat();
        philosopher.putFork(philosopher.rightFork);
        philosopher.putFork(philosopher.leftFork);
    }
}
