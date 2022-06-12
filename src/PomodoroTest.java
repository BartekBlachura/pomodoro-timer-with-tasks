import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class PomodoroTest {
    Pomodoro pomodoro = new Pomodoro();

    @Test
    void pomodoroWork() throws InterruptedException {
        pomodoro.setWorkTimeSeconds(10);
        long startTime = System.currentTimeMillis();
        pomodoro.pomodoroWork();
        System.out.println( System.currentTimeMillis()-startTime);
    }

//    @ParameterizedTest
//    @ValueSource(ints = {5, 10, 15, 30})
//    void pomodoroWorkSeconds(int seconds) {
//        long acceptableError = (long) (((1.0 / 3600) * 1000) * seconds);
//        pomodoro.setWorkTimeSeconds(seconds);
//        assertTimeout(Duration.ofSeconds(seconds + acceptableError), () -> pomodoro.pomodoroWork());
//    }
//
//    @Test
//    void pomodoroWorkTime() {
//        pomodoro.setWorkTimeSeconds(10);
//        assertTimeout(Duration.ofSeconds(11), () -> pomodoro.pomodoroWork());
//    }

    @Test
    void pomodoroBreak() {
//        pomodoro.setEndedWorkPhases(4);
//        long startTime = System.currentTimeMillis();
        pomodoro.pomodoroBreak();
//        System.out.println( System.currentTimeMillis()-startTime);
    }

    @Test
    void pomodoro() {
        for (int i = 1; i < 10; i++) {
            System.out.println("i = " + i);
            pomodoro.start();
            System.out.println(pomodoro.getEndedWorkPhases());
        }
    }
}