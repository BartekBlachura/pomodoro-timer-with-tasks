import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean previousCompleted = true;
    private static int tasksToAdd = 0;

    private static int inputInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        boolean back = false;
        while (!back) {
            System.out.println("--------------------");
            System.out.println("SETTINGS");
            System.out.println("--------------------");
            System.out.println("current work time: " + pomodoro.getWorkTimeMinutes());
            System.out.println("current short break time: " + pomodoro.getShortBreakTimeMinutes());
            System.out.println("current long break time: " + pomodoro.getLongBreakTimeMinutes());
            System.out.println("current username: " + pomodoro.getUserName().replace("_", " "));
            System.out.println("--------------------");
            System.out.println("""
                    select:
                    1 - set a work time
                    2 - set a short break time
                    3 - set a long break time
                    4 - set username
                    9 - back""");

            System.out.print("input number: ");
            switch (inputInt()) {
                case 1 -> {
                    System.out.print("input new work time: ");
                    pomodoro.setWorkTimeMinutes(inputInt());
                }
                case 2 -> {
                    System.out.print("input new short break time: ");
                    pomodoro.setShortBreakTimeMinutes(inputInt());
                }
                case 3 -> {
                    System.out.print("input new long break time: ");
                    pomodoro.setLongBreakTimeMinutes(inputInt());
                }
                case 4 -> setUserName(pomodoro);
                case 9 -> {
                    saveSettings(pomodoro);
                    back = true;
                }
                default -> System.out.println("input correct number");
            }
        }
    }

    private static void setUserName(Pomodoro pomodoro) {
        while (true){
            String userName;
            System.out.print("input new username (1-10 characters - letters, numbers, space): ");
            try {
                userName = scanner.nextLine().replace(" ", "_");
                if (userName.matches("\\w{1,20}")) {
                    pomodoro.setUserName(userName);
                    break;
                } else {
                    System.out.println("input new username (1-10 characters - letters, numbers, space)");
                }
            } catch (Exception e) {
                System.out.println("input new username (1-10 characters - letters, numbers, space)");
                System.out.println(e.getMessage());
            }
        }
    }

    public static void saveSettings(Pomodoro pomodoro) {
        try {
            pomodoro.saveSettings();
            System.out.println("settings have been saved");
        } catch (Exception e) {
            System.out.println("failed to save settings");
            System.out.println(e.getMessage());
        }
    }

    private static void tasks(ListOfTasks listOfTasks, String userName) {
        boolean back = false;
        while (!back) {
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
            switch (inputInt()) {
                case 1 -> addTask(listOfTasks, userName);
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
                case 5 -> markAsDone(listOfTasks, userName);
                case 6 -> {
                    listOfTasks.sortTasksByPriority();
                    System.out.println("tasks have been sorted");
                }
                case 9 -> {
                    saveTask(listOfTasks);
                    back = true;
                }
                default -> System.out.println("input correct number");
            }
        }
    }

    private static void addTask(ListOfTasks listOfTasks, String userName) {
        int ID = ++listOfTasks.lastID;
        String name;
        boolean shortTask;
        int priority;

        while (true) {
            System.out.print("input task name (1-20 characters - letters, numbers, space): ");
            try {
                name = scanner.nextLine().replace(" ", "_");
                if (name.matches("\\w{1,20}")) {
                    break;
                } else {
                    System.out.println("input correct name (1-20 characters - letters, numbers, space)");
                }
            } catch (Exception e) {
                System.out.println("input correct name (1-20 characters - letters, numbers, space)");
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            System.out.print("is it a short task - less than 5 min (Y/N): ");
            try {
                String answer = scanner.nextLine();
                if (answer.equals("Y") || answer.equals("y")) {
                    shortTask = true;
                    break;
                }
                if (answer.equals("N") || answer.equals("n")) {
                    shortTask = false;
                    break;
                } else {
                    System.out.println("input correct answer (Y/N)");
                }
            } catch (Exception e) {
                System.out.println("input correct answer (Y/N)");
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            System.out.print("give priority (1-3): ");
            try {
                priority = Integer.parseInt(scanner.nextLine());
                if (1 <= priority && priority <= 3 ) {
                    break;
                } else {
                    System.out.println("input correct priority (1-3)");
                }
            } catch (Exception e) {
                System.out.println("input correct priority (1-3)");
                System.out.println(e.getMessage());
            }
        }
        listOfTasks.addTaskToList(new Task(ID, name, shortTask, priority, userName));
        System.out.println("task has been added");
    }

    private static void markAsDone(ListOfTasks listOfTasks, String userName){
        while (true) {
            System.out.print("input task ID: ");
            try {
                if(listOfTasks.markAsCompleted(Integer.parseInt(scanner.nextLine()),userName)){
                    System.out.println("task marked as done");
                } else {
                    System.out.println("there is no task with the given ID");
                }
                break;
            }
            catch (Exception e){
                System.out.println("input correct number");
                System.out.println(e.getMessage());
            }
        }
    }

    public static void saveTask(ListOfTasks listOfTasks) {
        try {
            listOfTasks.saveTasksLists();
            System.out.println("tasks have been saved");
        } catch (Exception e) {
            System.out.println("failed to save the tasks lists");
            System.out.println(e.getMessage());
        }
    }

    public static void markPreviousCompleted(ListOfTasks listOfTasks, Pomodoro pomodoro) {
        int nonCompleted = listOfTasks.getListToDo().size();
        ArrayList<Integer> tasksToRemove = new ArrayList<>();

        System.out.println("was previous completed?");
        for (Task task : listOfTasks.getListToDo()) {
            task.printTask();
            while (true) {
                System.out.print("has this task been completed? (Y/N): ");
                try {
                    String answer = scanner.nextLine();
                    if (answer.equals("Y") || answer.equals("y")) {
                        tasksToRemove.add(task.getID());
                        break;
                    }
                    if (answer.equals("N") || answer.equals("n")) {
                        break;
                    } else {
                        System.out.println("input correct answer (Y/N)");
                    }
                } catch (Exception e) {
                    System.out.println("input correct answer (Y/N)");
                    System.out.println(e.getMessage());
                }
            }
        }
        for (Integer ID : tasksToRemove) {
            listOfTasks.markAsCompleted(ID, pomodoro.getUserName());
            nonCompleted--;
        }
        previousCompleted = nonCompleted == 0;

        if (!previousCompleted && listOfTasks.getListToDo().size() < 4) {
            while (true) {
                System.out.print("how many short tasks do you want to add to the list (>=0)? ");
                 try {
                     tasksToAdd = Integer.parseInt(scanner.nextLine());
                     if (tasksToAdd >= 0) {
                         break;
                     } else {
                         System.out.println("input correct amount (>=0)");
                     }
                 }
                 catch (Exception e) {
                     System.out.println("input correct amount (>=0)");
                     System.out.println(e.getMessage());
                 }
            }
        }
    }

    public static void main(String[] args) {
        Pomodoro pomodoro = new Pomodoro();
        ListOfTasks listOfTasks = new ListOfTasks();

        try {
            pomodoro.loadSettings();
        }
        catch (Exception e) {
            System.out.println("failed to load settings");
            System.out.println(e.getMessage());
        }
        listOfTasks.loadTasksLists();

        System.out.println("--------------------");
        System.out.println("POMODORO TIMER WITH TASK LIST");

        boolean exit = false;
        while (!exit) {
            System.out.println("--------------------");

            if (pomodoro.isBreakPhase()){
                markPreviousCompleted(listOfTasks, pomodoro);
                System.out.println("--------------------");
            }

            if (pomodoro.isWorkPhase()) {
                System.out.println("it's time to work!");
                listOfTasks.printTaskToDo(pomodoro.getEndedWorkPhases(), previousCompleted, tasksToAdd);
            } else {
                System.out.println("it's time to rest!");
            }
            System.out.println("--------------------");

            displayMenu();

            System.out.print("input number: ");
            switch (inputInt()) {
                case 0 -> {
                    pomodoro.start();
                    System.out.println();
                }
                case 1 -> tasks(listOfTasks, pomodoro.getUserName());
                case 2 -> settings(pomodoro);
                case 8 -> {
                    pomodoro.setWorkTimeSeconds(5);
                    pomodoro.setShortBreakTimeSeconds(3);
                    pomodoro.setLongBreakTimeSeconds(4);
                }
                case 9 -> {
                    System.out.println("thanks for use");
                    exit = true;
                }
                default -> System.out.println("input correct number");
            }
        }
        saveSettings(pomodoro);
        saveTask(listOfTasks);
        scanner.close();
    }
}

// TODO log4J ? how to save logs?
