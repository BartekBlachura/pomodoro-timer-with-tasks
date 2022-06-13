import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ListOfTasks {

    private static ArrayList<Task> listOfShortTasks = new ArrayList<>();
    private static ArrayList<Integer> listIdOfShortTasks = new ArrayList<>();
    private static ArrayList<Task> listOfLongTasks = new ArrayList<>();
    private static ArrayList<Integer> listIdOfLongTasks = new ArrayList<>();
    private static ArrayList<Task> listOfCompletedTasks = new ArrayList<>();
    private static ArrayList<Integer> listIdOfCompletedTasks = new ArrayList<>();
    private static String pathListOfShortTasks = "short-tasks.txt";
    private static String pathListOfLongTasks = "long-tasks.txt";
    private static String pathListOfCompletedTasks = "completed-tasks.txt";
    private static String splitMark = "###";


    public static ArrayList<Task> getListOfShortTasks() {
        return listOfShortTasks;
    }

    public static ArrayList<Task> getListOfLongTasks() {
        return listOfLongTasks;
    }

    public static ArrayList<Task> getListOfCompletedTasks() {
        return listOfCompletedTasks;
    }

    public static void addTaskToList(Task task) {
        if (task.getEstimatedTime() < Pomodoro.getWorkTimeMinutes()) {
            listOfShortTasks.add(task);
        }
        else {
            listOfLongTasks.add(task);
        }
    }

    public static void printListOfTasks(ArrayList<Task> listOfTasks) {
        for (Task task : listOfTasks) {
            System.out.println("task no: "+task.getID()
                    +" | name: "+task.getName()
                    +" | estimated time: "+task.getEstimatedTime()
                    +" | priority: "+task.getPriority()
                    +" | creation date: "+task.getCreationDate()
                    +" | created by: "+task.getCreatedBy()
                    +" | edit date: "+task.getEditDate()
                    +" | edited by: "+task.getEditedBy());
        }
    }

    public static void loadTasksLists() throws FileNotFoundException, ParseException {
        Scanner scanner;
        String[] tmpTask;

        scanner = new Scanner(new File(pathListOfShortTasks));
        listOfShortTasks.clear();
        while (scanner.hasNextLine()){
            tmpTask = scanner.nextLine().split(splitMark);
            listOfShortTasks.add(new Task(tmpTask));
            listIdOfShortTasks.add(Integer.valueOf(tmpTask[0]));
        }
        scanner.close();

        scanner = new Scanner(new File(pathListOfLongTasks));
        listOfLongTasks.clear();
        while (scanner.hasNextLine()){
            tmpTask = scanner.nextLine().split(splitMark);
            listOfLongTasks.add(new Task(tmpTask));
            listIdOfLongTasks.add(Integer.valueOf(tmpTask[0]));
        }
        scanner.close();

        scanner = new Scanner(new File(pathListOfCompletedTasks));
        listOfCompletedTasks.clear();
        while (scanner.hasNextLine()){
            tmpTask = scanner.nextLine().split(splitMark);
            listOfCompletedTasks.add(new Task(tmpTask));
            listIdOfCompletedTasks.add(Integer.valueOf(tmpTask[0]));
        }
        scanner.close();
    }

    public static void saveTasksLists() throws FileNotFoundException {
        PrintWriter printWriter;

        printWriter = new PrintWriter(pathListOfShortTasks);
        for (Task task : listOfShortTasks) {
            printWriter.println(task.getID()+splitMark
                    +task.getName()+splitMark
                    +task.getEstimatedTime()+splitMark
                    +task.getPriority()+splitMark
                    +task.getCreationDate()+splitMark
                    +task.getCreatedBy()+splitMark
                    +task.getEditDate()+splitMark
                    +task.getEditedBy());
        }
        printWriter.close();

        printWriter = new PrintWriter(pathListOfLongTasks);
        for (Task task : listOfLongTasks) {
            printWriter.println(task.getID()+splitMark
                    +task.getName()+splitMark
                    +task.getEstimatedTime()+splitMark
                    +task.getPriority()+splitMark
                    +task.getCreationDate()+splitMark
                    +task.getCreatedBy()+splitMark
                    +task.getEditDate()+splitMark
                    +task.getEditedBy());
        }
        printWriter.close();

        printWriter = new PrintWriter(pathListOfCompletedTasks);
        for (Task task : listOfCompletedTasks) {
            printWriter.println(task.getID()+splitMark
                    +task.getName()+splitMark
                    +task.getEstimatedTime()+splitMark
                    +task.getPriority()+splitMark
                    +task.getCreationDate()+splitMark
                    +task.getCreatedBy()+splitMark
                    +task.getEditDate()+splitMark
                    +task.getEditedBy());
        }
        printWriter.close();
    }

    public static void markAsCompleted(int ID) {
        if (listIdOfShortTasks.contains(ID)) {
            int index = listIdOfShortTasks.indexOf(ID);
            listOfCompletedTasks.add(listOfShortTasks.get(index));
            listOfShortTasks.remove(index);
        }
        if (listIdOfLongTasks.contains(ID)) {
            int index = listIdOfLongTasks.indexOf(ID);
            listOfCompletedTasks.add(listOfLongTasks.get(index));
            listOfLongTasks.remove(index);
        }
    }

    public static Task getFirstShortTask(){
        return getListOfShortTasks().get(0);
    }

    public static Task getFirstLongTask(){
        return getListOfLongTasks().get(0);
    }
}
