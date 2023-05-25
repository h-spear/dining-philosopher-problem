import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Fork {

    private final int id;
    private final Semaphore semaphore;

    public Fork(int id) {
        this.id = id;
        this.semaphore = new Semaphore(1);
    }

    public void take() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean take(long milliseconds) {
        try {
            return semaphore.tryAcquire(milliseconds, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void put() {
        semaphore.release();
    }

    public int getId() {
        return id;
    }
}
