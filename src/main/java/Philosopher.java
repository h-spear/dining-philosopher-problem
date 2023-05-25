public abstract class Philosopher extends Thread {

    protected final int id;
    protected final Fork leftFork;
    protected final Fork rightFork;
    private int eatCount;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    protected abstract void execute();

    @Override
    public void run() {
        try {
            execute();
        } catch (Exception ignored) {
            // ignore...
        } finally {
            System.out.printf("Philosopher %d: terminated.\n", id);
        }
    }

    protected void think() {
        System.out.printf("Philosopher %d: thinking...\n", id);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void eat() {
        ++eatCount;
        System.out.printf("Philosopher %d: yum-yum, spaghetti.\n", id);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void takeFork(Fork fork) {
        fork.take();
        System.out.printf("Philosopher %d: take %d fork", id, fork.getId());
        System.out.printf(" with %s hand.\n", fork == leftFork ? "left" : "right");
    }

    protected void putFork(Fork fork) {
        fork.put();
        System.out.printf("Philosopher %d: put %d fork back on the table.\n", id, fork.getId());
    }

    public int getEatCount() {
        return eatCount;
    }
}
