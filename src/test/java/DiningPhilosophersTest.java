import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DiningPhilosophersTest {

    private static final int N = 5;
    private static final long RUNNING_TIME = 3 * 1000;
    private static final List<Fork> forks = new ArrayList<>();
    private static final List<Philosopher> philosophers = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        for (int i = 0; i < N; ++i) {
            forks.add(new Fork(i));
        }
    }

    @AfterEach
    void afterEach() throws InterruptedException {
        Thread.sleep(RUNNING_TIME);
        stop();
        Thread.sleep(100L);
        printResult();
        forks.clear();
        philosophers.clear();
    }

    @Test
    @DisplayName("데드락이 발생한다.")
    void diningPhilosophers_Deadlock() {
        for (int i = 0; i < N; ++i) {
            philosophers.add(new Philosopher_Deadlock(i, forks.get(i), forks.get((i + 1) % N)));
        }
        for (int i = 0; i < N; ++i) {
            philosophers.get(i).start();
        }
    }

    @Test
    @DisplayName("순환대기 예방: 짝수번째 철학자는 왼쪽 포크 먼저, 홀수번째 철학자는 오른쪽 포크를 먼저 잡도록하면 데드락이 발생하지 않는다.")
    void diningPhilosophers_CircularWaitPrevention() {
        for (int i = 0; i < N; ++i) {
            philosophers.add(new Philosopher_CircularWaitPrevention(i, forks.get(i), forks.get((i + 1) % N)));
        }
        for (int i = 0; i < N; ++i) {
            philosophers.get(i).start();
        }
    }

    @Test
    @DisplayName("오른쪽 포크를 일정시간동안 잡지 못할 경우 왼쪽 포크를 내려놓도록 하면 데드락이 발생하지 않는다.")
    void diningPhilosophers_Timeout() {
        for (int i = 0; i < N; ++i) {
            philosophers.add(new Philosopher_Timeout(i, forks.get(i), forks.get((i + 1) % N)));
        }
        for (int i = 0; i < N; ++i) {
            philosophers.get(i).start();
        }
    }

    @Test
    @DisplayName("synchronized 키워드를 사용하면 데드락이 발생하지 않는다.")
    void diningPhilosophers_Synchronized() {
        for (int i = 0; i < N; ++i) {
            philosophers.add(new Philosopher_Synchronized(i, forks.get(i), forks.get((i + 1) % N)));
        }
        for (int i = 0; i < N; ++i) {
            philosophers.get(i).start();
        }
    }

    private void stop() {
        for (int i = 0; i < N; ++i) {
            philosophers.get(i).interrupt();
        }
        for (int i = 0; i < N; ++i) {
            forks.get(i).put();
        }
    }

    private void printResult() {
        System.out.printf("==============================\n");
        for (int i = 0; i < N; ++i) {
            System.out.printf("Philosopher %d ate %d times.\n", i, philosophers.get(i).getEatCount());
        }
    }
}
