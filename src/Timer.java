public class Timer {
    public static boolean countdown (int seconds) {
        long startTime = System.currentTimeMillis();
        Pomodoro.setTimeLeft(seconds);

        while (seconds > 0) {
            if ((System.currentTimeMillis() - startTime) >= 1000) {
                seconds--;
                Pomodoro.setTimeLeft(seconds);
                startTime = System.currentTimeMillis();
            }
        }
        return true;
    }
}
