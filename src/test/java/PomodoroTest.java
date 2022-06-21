import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class PomodoroTest {
    Pomodoro pomodoro = new Pomodoro();

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 30})
    void pomodoroWorkSeconds(int seconds) {
        pomodoro.setWorkTimeSeconds(seconds);
        assertTimeout(Duration.ofMillis(1010L * seconds), () -> pomodoro.pomodoroWork());
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 30})
    void pomodoroShortBreakSeconds(int seconds) {
        pomodoro.setShortBreakTimeSeconds(seconds);
        pomodoro.setLongBreakTimeSeconds(0);
        pomodoro.setEndedWorkPhases(seconds);
        assertTimeout(Duration.ofMillis(1010L * seconds), () -> pomodoro.pomodoroBreak());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 8, 12, 16})
    void pomodoroLongBreakSeconds(int seconds) {
        pomodoro.setShortBreakTimeSeconds(0);
        pomodoro.setLongBreakTimeSeconds(seconds);
        pomodoro.setEndedWorkPhases(seconds);
        assertTimeout(Duration.ofMillis(1010L * seconds), () -> pomodoro.pomodoroBreak());
    }

    @Test
    void settings() throws IOException {
        pomodoro.setUserName("Test");
        pomodoro.setWorkTimeMinutes(27);
        pomodoro.setShortBreakTimeMinutes(7);
        pomodoro.setLongBreakTimeMinutes(17);
        pomodoro.saveSettings();

        Pomodoro pomodoroTest = new Pomodoro();
        pomodoroTest.loadSettings();
        assertEquals("Test", pomodoroTest.getUserName());
        assertEquals(27, pomodoroTest.getWorkTimeMinutes());
        assertEquals(7, pomodoroTest.getShortBreakTimeMinutes());
        assertEquals(17, pomodoroTest.getLongBreakTimeMinutes());
    }

//    @ParameterizedTest
//    @ValueSource(ints = {1, 5, 10, 15, 30})
//    void pomodoroWorkMinutes(int minutes) {
//        pomodoro.setWorkTimeMinutes(minutes);
//        assertTimeout(Duration.ofMillis(60600L * minutes), () -> pomodoro.pomodoroWork());
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {1, 5, 10, 15, 30})
//    void pomodoroShortBreakMinutes(int minutes) {
//        pomodoro.setShortBreakTimeMinutes(minutes);
//        pomodoro.setLongBreakTimeMinutes(0);
//        assertTimeout(Duration.ofMillis(60600L * minutes), () -> pomodoro.pomodoroBreak());
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {4, 8, 12, 16})
//    void pomodoroLongBreakMinutes(int minutes) {
//        pomodoro.setShortBreakTimeMinutes(0);
//        pomodoro.setLongBreakTimeMinutes(minutes);
//        assertTimeout(Duration.ofMillis(60600L * minutes), () -> pomodoro.pomodoroBreak());
//    }
}