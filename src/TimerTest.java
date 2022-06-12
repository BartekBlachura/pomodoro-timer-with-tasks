import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 30})
    void countdownSeconds(int seconds) {
        assertTimeout(Duration.ofSeconds(seconds), () -> Timer.countdown(seconds));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 30})
    void countdownMinutes(int minutes) {
        int seconds = minutes * 60;
        assertTimeout(Duration.ofMinutes(minutes), () -> Timer.countdown(seconds));
    }
}