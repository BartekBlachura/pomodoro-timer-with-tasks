import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfTasks {

    private ArrayList<Task> listToDo = new ArrayList<>();
    private ArrayList<Task> listOfShortTasks = new ArrayList<>();
    private ArrayList<Integer> listIdOfShortTasks = new ArrayList<>();
    private ArrayList<Task> listOfLongTasks = new ArrayList<>();
    private ArrayList<Integer> listIdOfLongTasks = new ArrayList<>();
    private ArrayList<Task> listOfCompletedTasks = new ArrayList<>();
    private ArrayList<Integer> listIdOfCompletedTasks = new ArrayList<>();
    private String pathListOfShortTasks = "short-tasks.txt";
    private String pathListOfLongTasks = "long-tasks.txt";
    private String pathListOfCompletedTasks = "completed-tasks.txt";
    private String splitMark = "###";


    public ArrayList<Task> getListOfShortTasks() {
        return listOfShortTasks;
    }

    public ArrayList<Task> getListOfLongTasks() {
        return listOfLongTasks;
    }

    public ArrayList<Task> getListOfCompletedTasks() {
        return listOfCompletedTasks;
    }

    public void addTaskToList(Task task) {
        if (task.isShortTask()) {
            listOfShortTasks.add(task);
        }
        else {
            listOfLongTasks.add(task);
        }
    }

    public void printListOfTasks(ArrayList<Task> listOfTasks) {
//        TODO if list is null!
        for (Task task : listOfTasks) {
            System.out.println("task no: "+task.getID()
                    +" | name: "+task.getName()
                    +" | short task: "+task.isShortTask()
                    +" | priority: "+task.getPriority()
                    +" | creation date: "+task.getCreationDate()
                    +" | created by: "+task.getCreatedBy()
                    +" | edit date: "+task.getEditDate()
                    +" | edited by: "+task.getEditedBy());
        }
    }

    public void loadTasksLists(){
        Scanner scanner;
        String[] tmpTask;

        try {
            scanner = new Scanner(new File(pathListOfShortTasks));
            listOfShortTasks.clear();
            while (scanner.hasNextLine()) {
                tmpTask = scanner.nextLine().split(splitMark);
                listOfShortTasks.add(new Task(tmpTask));
                listIdOfShortTasks.add(Integer.valueOf(tmpTask[0]));
            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("failed to load the short task list");
        }


        try {
            scanner = new Scanner(new File(pathListOfLongTasks));
            listOfLongTasks.clear();
            while (scanner.hasNextLine()){
                tmpTask = scanner.nextLine().split(splitMark);
                listOfLongTasks.add(new Task(tmpTask));
                listIdOfLongTasks.add(Integer.valueOf(tmpTask[0]));
            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("failed to load the long task list");
        }

        try {
            scanner = new Scanner(new File(pathListOfCompletedTasks));
            listOfCompletedTasks.clear();
            while (scanner.hasNextLine()){
                tmpTask = scanner.nextLine().split(splitMark);
                listOfCompletedTasks.add(new Task(tmpTask));
                listIdOfCompletedTasks.add(Integer.valueOf(tmpTask[0]));
            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("failed to load the completed task list");
        }
    }

    public void saveTasksLists() throws FileNotFoundException {
        PrintWriter printWriter;

        printWriter = new PrintWriter(pathListOfShortTasks);
        for (Task task : listOfShortTasks) {
            printWriter.println(task.getID()+splitMark
                    +task.getName()+splitMark
                    +task.isShortTask()+splitMark
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
                    +task.isShortTask()+splitMark
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
                    +task.isShortTask()+splitMark
                    +task.getPriority()+splitMark
                    +task.getCreationDate()+splitMark
                    +task.getCreatedBy()+splitMark
                    +task.getEditDate()+splitMark
                    +task.getEditedBy());
        }
        printWriter.close();
    }

    public void markAsCompleted(int ID) {
        if (listIdOfShortTasks.contains(ID)) {
            int index = listIdOfShortTasks.indexOf(ID);
            listOfCompletedTasks.add(listOfShortTasks.get(index));
            listIdOfCompletedTasks.add(ID);
            listOfShortTasks.remove(index);
            listIdOfShortTasks.remove(index);
        }
        if (listIdOfLongTasks.contains(ID)) {
            int index = listIdOfLongTasks.indexOf(ID);
            listOfCompletedTasks.add(listOfLongTasks.get(index));
            listIdOfCompletedTasks.add(ID);
            listOfLongTasks.remove(index);
            listIdOfLongTasks.remove(index);
        }
    }

    public ArrayList<Task> getFirst4ShortTask(){
        ArrayList<Task> tmp = new ArrayList<>();
        for(int i = 0; i < 4; i++ ){
            try {
                tmp.add(getListOfShortTasks().get(i));
            }
            catch (Exception e){
            }
        }
        return tmp;
    }

    public Task getFirstLongTask(){
        return getListOfLongTasks().get(0);
    }

    public void sortTasksByPriority() {

        ArrayList<Task> tmpShort = new ArrayList<>();
        ArrayList<Task> tmpLong = new ArrayList<>();

        for (int priority = 3; priority > 0; priority--) {
            for (Task task: listOfShortTasks) {
                if (task.getPriority() == priority) {
                    tmpShort.add(task);
                }
            }
            for (Task task: listOfLongTasks) {
                if (task.getPriority() == priority) {
                    tmpLong.add(task);
                }
            }
        }
        listOfShortTasks = tmpShort;
        listOfLongTasks = tmpLong;
    }

    public void printTaskToDo(int endedWorkPhases) {
//        TODO if list is null!
        listToDo.clear();
        System.out.print("you should work on ");
        if (endedWorkPhases % 2 == 0) {
            System.out.println("that 4 short task:");
            listToDo = getFirst4ShortTask();
        }
        else {
            System.out.println("that 1 long task:");
            listToDo.add(getFirstLongTask());
        }
        for (Task task : listToDo) {
            task.printTask();
        }
    }
}
