public class Philosopher_Timeout extends Philosopher {

    public Philosopher_Timeout(int id, Fork leftFork, Fork rightFork) {
        super(id, leftFork, rightFork);
    }

    @Override
    protected void execute() {
        while (!Thread.currentThread().isInterrupted()) {
            think();
            boolean b = takeForks();
            if (b) {
                eat();
                putFork(rightFork);
                putFork(leftFork);
            }
        }
    }

    private boolean takeForks() {
        takeFork(leftFork);
        boolean flag = rightFork.take(150);
        if (flag) {
            System.out.printf("Philosopher %d: take %d fork with right hand.\n", id, rightFork.getId());
        } else {
            putFork(leftFork);
        }
        return flag;
    }
}
