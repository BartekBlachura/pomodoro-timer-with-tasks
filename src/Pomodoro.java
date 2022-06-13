import java.util.Scanner;

public class Pomodoro {
    private int workTime = 25 * 60;         // seconds
    private int shortBreakTime = 5 * 60;    // seconds
    private int longBreakTime = 15 * 60;    // seconds
    private boolean workPhase = true;
    private boolean breakPhase = false;
    private int endedWorkPhases = 0;
    private int timeLeft;

    public Pomodoro(){}

    public int getWorkTimeMinutes() {
        return workTime / 60;
    }

    public int getShortBreakTimeMinutes() {
        return shortBreakTime / 60;
    }

    public int getLongBreakTimeMinutes() {
        return longBreakTime / 60;
    }

    public int getTimeLeftMinutes() {
        if(timeLeft % 60 == 0) {
            return timeLeft / 60;
        }
        else {
            return (timeLeft / 60) + 1;
        }
    }

    public void setWorkTimeMinutes(int minutes) {
        this.workTime = minutes * 60;  // minutes -> seconds
    }

    public void setWorkTimeSeconds(int seconds) {
        this.workTime = seconds;
    }

    public void setShortBreakTimeMinutes(int minutes) {
        this.shortBreakTime = minutes * 60;  // minutes -> seconds
    }

    public void setShortBreakTimeSeconds(int seconds) {
        this.shortBreakTime = seconds;
    }

    public void setLongBreakTimeMinutes(int minutes) {
        this.longBreakTime = minutes * 60;  // minutes -> seconds
    }

    public void setLongBreakTimeSeconds(int seconds) {
        this.longBreakTime = seconds;
    }

    public void setTimeLeft(int seconds) {
        timeLeft = seconds;
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


    public boolean pomodoroWork(){
        long startTime = System.currentTimeMillis();
        timeLeft = workTime;
        int tmpTime = 0;
//        System.out.println("stay focused: " + timeLeft);

        while (timeLeft > 0) {
            if ((System.currentTimeMillis() - startTime) >= 1000) {
                timeLeft--;
                startTime = System.currentTimeMillis();
//                System.out.println("stay focused: " + timeLeft);
            }
            if (tmpTime != getTimeLeftMinutes()) {
                tmpTime = getTimeLeftMinutes();
                System.out.println("time to focused: " + tmpTime + " min");
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

        long startTime = System.currentTimeMillis();
        timeLeft = breakTime;
        int tmpTime = 0;
//        System.out.println("time to rest: " + timeLeft);

        while (timeLeft > 0) {
            if ((System.currentTimeMillis() - startTime) >= 1000) {
                timeLeft--;
                startTime = System.currentTimeMillis();
//                System.out.println("time to rest: " + timeLeft);
            }
            if (tmpTime != getTimeLeftMinutes()) {
                tmpTime = getTimeLeftMinutes();
                System.out.println("time to rest: " + tmpTime + " min");
            }
        }
        return true;
    }

    public void start() {
        if (workPhase) {
            breakPhase = pomodoroWork();
            workPhase = !breakPhase;
        }
        else {
            workPhase = pomodoroBreak();
            breakPhase = !workPhase;
        }
    }

    public void displayMenu() {
        System.out.println("""
                select:
                0 - start
                1 - set a work time
                2 - set a short break time
                3 - set a long break time
                9 - exit""");
    }

//    public Thread displayTime() {
//        int tmpTime = 99;
//        while(tmpTime !=0) {
//            if (tmpTime != getTimeLeftMinutes()) {
//                tmpTime = getTimeLeftMinutes();
//                if (isWorkPhase()) {
//                    System.out.println("stay focused: " + tmpTime + " min");
//                }
//                else {
//                    System.out.println("time to rest: " + tmpTime + " min");
//                }
//            }
//        }
//        return null;
//    }

    public static void main(String[] args) {
        Pomodoro pomodoro = new Pomodoro();

        int selectedOption = 8;

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

//            pomodoro.setWorkTimeMinutes(2);
//            pomodoro.setWorkTimeSeconds(15);
//            pomodoro.setShortBreakTimeMinutes(2);
//            pomodoro.setShortBreakTimeMinutes(2);

            switch (selectedOption) {
                case 0 -> {
//                    new Thread(pomodoro::displayTime).start();
                    pomodoro.start();
                    System.out.println();
                }
                case 1 -> {
                    System.out.println("current work time: " + pomodoro.getWorkTimeMinutes());
                    System.out.print("input new work time: ");
                    pomodoro.setWorkTimeMinutes(scanner.nextInt());
                }
                case 2 -> {
                    System.out.println("current short break time: " + pomodoro.getShortBreakTimeMinutes());
                    System.out.print("input new short break time: ");
                    pomodoro.setShortBreakTimeMinutes(scanner.nextInt());
                }
                case 3 -> {
                    System.out.println("current long break time: " + pomodoro.getLongBreakTimeMinutes());
                    System.out.print("input new long break time: ");
                    pomodoro.setLongBreakTimeMinutes(scanner.nextInt());
                }
                case 9 -> System.out.println("thanks for use");
                default -> System.out.println("input correct number");
            }
        }
        scanner.close();
    }
}
