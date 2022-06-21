import java.io.*;

public class Pomodoro {
    private String userName = "User";
    private int workTime = 25 * 60;         // seconds
    private int shortBreakTime = 5 * 60;    // seconds
    private int longBreakTime = 15 * 60;    // seconds
    private boolean workPhase = true;
    private boolean breakPhase = false;
    private int endedWorkPhases = 0;
    private int timeLeft;

    public Pomodoro(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public boolean isWorkPhase() {
        return workPhase;
    }

    public boolean isBreakPhase() {
        return breakPhase;
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

        while (timeLeft > 0) {
            if ((System.currentTimeMillis() - startTime) >= 1000) {
                timeLeft--;
                startTime = System.currentTimeMillis();
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

        while (timeLeft > 0) {
            if ((System.currentTimeMillis() - startTime) >= 1000) {
                timeLeft--;
                startTime = System.currentTimeMillis();
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

    public void loadSettings() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("settings.dat"));
        userName = dataInputStream.readUTF();
        workTime = dataInputStream.readInt();
        shortBreakTime = dataInputStream.readInt();
        longBreakTime = dataInputStream.readInt();
    }

    public void saveSettings() throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("settings.dat"));
        dataOutputStream.writeUTF(userName);
        dataOutputStream.writeInt(workTime);
        dataOutputStream.writeInt(shortBreakTime);
        dataOutputStream.writeInt(longBreakTime);
    }
}
