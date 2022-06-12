import java.util.Scanner;

public class Pomodoro {
//    private int workTime = 25 * 60;         // seconds
    private int workTime = 5;         // seconds
//    private int shortBreakTime = 5 * 60;    // seconds
//    private int longBreakTime = 15 * 60;    // seconds
    private int shortBreakTime = 3;    // seconds
    private int longBreakTime = 5;    // seconds
    private boolean workPhase = true;
    private boolean breakPhase = false;
    private int endedWorkPhases = 0;
    private static int timeLeft;            // seconds

    public Pomodoro(){}

    public int getWorkTime() {
        return workTime / 60;  // return in minutes
    }

    public int getShortBreakTime() {
        return shortBreakTime / 60;  // return in minutes
    }

    public int getLongBreakTime() {
        return longBreakTime / 60;  // return in minutes
    }

    public void setWorkTime(int minutes) {
        this.workTime = minutes * 60;  // minutes -> seconds
    }

    public void setWorkTimeSeconds(int seconds) {
        this.workTime = seconds;
    }

    public void setShortBreakTime(int minutes) {
        this.shortBreakTime = minutes * 60;  // minutes -> seconds
    }

    public void setLongBreakTime(int minutes) {
        this.longBreakTime = minutes * 60;  // minutes -> seconds
    }

    public boolean isWorkPhase() {
        return workPhase;
    }

    public void setWorkPhase(boolean workPhase) {
        this.workPhase = workPhase;
    }

    public boolean isBreakPhase() {
        return breakPhase;
    }

    public void setBreakPhase(boolean breakPhase) {
        this.breakPhase = breakPhase;
    }

    public int getEndedWorkPhases() {
        return endedWorkPhases;
    }

    public void setEndedWorkPhases(int endedWorkPhases) {
        this.endedWorkPhases = endedWorkPhases;
    }
    public static void setTimeLeft(int seconds) {
        timeLeft = seconds;
    }

    public boolean pomodoroWork(){
        Thread countdownThread = new Thread(() -> workPhase = !Timer.countdown(workTime));

        countdownThread.start();

        int tmpTimeLeft = 0;
        while (workPhase) {
            if (tmpTimeLeft != timeLeft) {
                tmpTimeLeft = timeLeft;
                System.out.println("stay focused: " + tmpTimeLeft);
            }
        }
        endedWorkPhases ++;
        return true;
    }

    public boolean pomodoroBreak() {
        int breakTime = shortBreakTime;
        if (endedWorkPhases % 4 == 0) {
            breakTime = longBreakTime;
        }

        int finalBreakTime = breakTime;
        Thread countdownThread = new Thread(() -> breakPhase = !Timer.countdown(finalBreakTime));

        countdownThread.start();

        int tmpTimeLeft = 0;
        while (breakPhase) {
            if (tmpTimeLeft != timeLeft) {
                tmpTimeLeft = timeLeft;
                System.out.println("time to rest: " + tmpTimeLeft);
            }
        }
        return true;
    }

    public void start() {
        if (workPhase) {
            breakPhase = pomodoroWork();
        }
        else {
            workPhase = pomodoroBreak();
        }
    }

    public void displayMenu() {
        System.out.println("""
                select:
                0 - start
                9 - exit""");
    }

    public static void main(String[] args) {
        Pomodoro pomodoro = new Pomodoro();

        for (int i = 1; i < 10; i++) {
            System.out.println("i = " + i);
            pomodoro.start();
            System.out.println(pomodoro.getEndedWorkPhases());
        }


        int selectedOption = 0;

        System.out.println("pomodoro timer");

        Scanner scanner = new Scanner(System.in);
        while (selectedOption != 9) {
            pomodoro.displayMenu();
            if (pomodoro.isWorkPhase()) {
                System.out.println("\nIt's time to work!\n");
            }
            else {
                System.out.println("\nIt's time to rest!\n");
            }

            System.out.print("input number: ");
            selectedOption = scanner.nextInt();
            if (selectedOption == 0) {
                pomodoro.start();
                System.out.println();
            }
        }
        scanner.close();
    }
}
