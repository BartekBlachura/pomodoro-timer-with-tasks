import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

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

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 30})
    void pomodoroWorkMinutes(int minutes) {
        pomodoro.setWorkTimeMinutes(minutes);
        int seconds = minutes * 60;
        assertTimeout(Duration.ofMillis(60600L * minutes), () -> pomodoro.pomodoroWork());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 30})
    void pomodoroShortBreakMinutes(int minutes) {
        pomodoro.setShortBreakTimeMinutes(minutes);
        pomodoro.setLongBreakTimeMinutes(0);
        assertTimeout(Duration.ofMillis(60600L * minutes), () -> pomodoro.pomodoroBreak());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 8, 12, 16})
    void pomodoroLongBreakMinutes(int minutes) {
        pomodoro.setShortBreakTimeMinutes(0);
        pomodoro.setLongBreakTimeMinutes(minutes);
        assertTimeout(Duration.ofMillis(60600L * minutes), () -> pomodoro.pomodoroBreak());
    }
}