public class Philosopher_CircularWaitPreventionHashCode extends Philosopher {

    private final boolean leftFirst;

    public Philosopher_CircularWaitPreventionHashCode(int id, Fork leftFork, Fork rightFork) {
        super(id, leftFork, rightFork);
        leftFirst = leftFork.hashCode() > rightFork.hashCode();
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
        if (leftFirst) {
            takeFork(leftFork);
            takeFork(rightFork);
        } else {
            takeFork(rightFork);
            takeFork(leftFork);
        }
    }

    private void putForks() {
        if (leftFirst) {
            putFork(rightFork);
            putFork(leftFork);
        } else {
            putFork(leftFork);
            putFork(rightFork);
        }
    }
}
