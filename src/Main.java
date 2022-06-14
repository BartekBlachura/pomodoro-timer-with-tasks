import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static int inputInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e) {
            return 99;
        }
    }

    private static void displayMenu() {
        System.out.println("""
                select:
                0 - start
                1 - tasks
                2 - pomodoro settings
                9 - exit""");
    }

    private static void settings(Pomodoro pomodoro) {
        int selectedOption = 99;
        while (selectedOption != 9) {
            System.out.println("--------------------");
            System.out.println("SETTINGS");
            System.out.println("--------------------");
            System.out.println("""
                select:
                1 - set a work time
                2 - set a short break time
                3 - set a long break time
                9 - back""");
            System.out.print("input number: ");
            selectedOption = inputInt();

            switch (selectedOption) {
                case 1 -> {
                    System.out.println("current work time: " + pomodoro.getWorkTimeMinutes());
                    System.out.print("input new work time: ");
                    pomodoro.setWorkTimeMinutes(inputInt());
                }
                case 2 -> {
                    System.out.println("current short break time: " + pomodoro.getShortBreakTimeMinutes());
                    System.out.print("input new short break time: ");
                    pomodoro.setShortBreakTimeMinutes(inputInt());
                }
                case 3 -> {
                    System.out.println("current long break time: " + pomodoro.getLongBreakTimeMinutes());
                    System.out.print("input new long break time: ");
                    pomodoro.setLongBreakTimeMinutes(inputInt());
                }
                case 9 -> System.out.println("settings saved");
                default -> {
                    System.out.println("input correct number");
                    settings(pomodoro);
                }
            }
        }
    }

    private static void tasks(ListOfTasks listOfTasks) {
        int selectedOption = 99;
        while (selectedOption != 9) {
            System.out.println("--------------------");
            System.out.println("TASKS");
            System.out.println("--------------------");
            System.out.println("""
                select:
                1 - add new task
                2 - show list of short tasks
                3 - show list of long tasks
                4 - show list of completed tasks
                5 - mark task as done
                6 - sort tasks by priority
                9 - back""");
            System.out.print("input number: ");
            selectedOption = inputInt();

            switch (selectedOption) {
                case 1 -> addTask(listOfTasks);
                case 2 -> {
                    System.out.println("list of short tasks:");
                    listOfTasks.printListOfTasks(listOfTasks.getListOfShortTasks());
                }
                case 3 -> {
                    System.out.println("list of long tasks:");
                    listOfTasks.printListOfTasks(listOfTasks.getListOfLongTasks());
                }
                case 4 -> {
                    System.out.println("list of completed tasks:");
                    listOfTasks.printListOfTasks(listOfTasks.getListOfCompletedTasks());
                }
                case 5 -> markAsDone(listOfTasks);
                case 6 -> {
                    listOfTasks.sortTasksByPriority();
                    System.out.println("tasks have been sorted");
                }
                case 9 -> saveTask(listOfTasks);
                default -> {
                    System.out.println("input correct number");
                    tasks(listOfTasks);
                }
            }
        }
    }

    private static void addTask(ListOfTasks listOfTasks){
        System.out.println("add new task");
    }

    private static void markAsDone(ListOfTasks listOfTasks){
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print("input task ID: ");
            try {
                if(listOfTasks.markAsCompleted(Integer.parseInt(scanner.nextLine()))){
                    System.out.println("task marked as done");
                }
                else {
                    System.out.println("there is no task with the given ID");
                }
                correctInput = true;
            }
            catch (Exception e){
                System.out.println("input correct number");
            }
        }
    }

    public static void saveTask(ListOfTasks listOfTasks) {
        try {
            listOfTasks.saveTasksLists();
            System.out.println("changes have been saved");
        } catch (Exception e) {
            System.out.println("failed to save the task list");
        }
    }

    public static void main(String[] args) {
        Pomodoro pomodoro = new Pomodoro();
        ListOfTasks listOfTasks = new ListOfTasks();
        listOfTasks.loadTasksLists();

        System.out.println("--------------------");
        System.out.println("POMODORO TIMER WITH TASK LIST");

        int selectedOption = 99;
        while (selectedOption != 9) {
            System.out.println("--------------------");
            if (pomodoro.isWorkPhase()) {
                System.out.println("it's time to work!");
                listOfTasks.printTaskToDo(pomodoro.getEndedWorkPhases());
            }
            else {
                System.out.println("it's time to rest!");
            }
            System.out.println("--------------------");
            displayMenu();

            System.out.print("input number: ");
            selectedOption = inputInt();

            switch (selectedOption) {
                case 0 -> {
                    pomodoro.start();
                    System.out.println();
                }
                case 1 -> tasks(listOfTasks);
                case 2 -> settings(pomodoro);
                case 8 -> {
                    pomodoro.setWorkTimeSeconds(5);
                    pomodoro.setShortBreakTimeSeconds(3);
                    pomodoro.setLongBreakTimeSeconds(4);
                }
                case 9 -> System.out.println("thanks for use");
                default -> System.out.println("input correct number");
            }
        }
        saveTask(listOfTasks);
        scanner.close();
    }
}

// TODO log4J ? zapis logów dla javy
