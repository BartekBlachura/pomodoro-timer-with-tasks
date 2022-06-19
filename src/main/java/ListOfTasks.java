import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class ListOfTasks {
    private static final Logger logger = LogManager.getLogger(ListOfTasks.class);

    private ArrayList<Task> listToDo = new ArrayList<>();
    private ArrayList<Task> listOfShortTasks = new ArrayList<>();
    private ArrayList<Integer> listIdOfShortTasks = new ArrayList<>();
    private ArrayList<Task> listOfLongTasks = new ArrayList<>();
    private ArrayList<Integer> listIdOfLongTasks = new ArrayList<>();
    private ArrayList<Task> listOfCompletedTasks = new ArrayList<>();
    private  ArrayList<Integer> listIdOfCompletedTasks = new ArrayList<>();
    private final String pathListOfShortTasks = "short-tasks.txt";
    private final String pathListOfLongTasks = "long-tasks.txt";
    private final String pathListOfCompletedTasks = "completed-tasks.txt";
    private final String splitMark = "###";
    public int lastID;

    public ArrayList<Task> getListToDo() {
        return listToDo;
    }

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
        } else {
            listOfLongTasks.add(task);
        }
    }

    public void printListOfTasks(ArrayList<Task> listOfTasks) {
        if(listOfTasks.size() > 0) {
            for (Task task : listOfTasks) {
                System.out.println("task no: "+task.getID()
                        +" | name: "+task.getName().replace("_", " ")
                        +" | short task: "+task.isShortTask()
                        +" | priority: "+task.getPriority()
                        +" | creation date: "+task.getCreationDate()
                        +" | created by: "+task.getCreatedBy()
                        +" | edit date: "+task.getEditDate()
                        +" | edited by: "+task.getEditedBy());
            }
        } else {
            System.out.println("no data");
        }
    }

    public void loadTasksLists(){
        Scanner scanner;
        String[] tmpTask;
        int tmpID;
        int tmpLastID = 0;

        try {
            scanner = new Scanner(new File(pathListOfShortTasks));
            listOfShortTasks.clear();
            while (scanner.hasNextLine()) {
                tmpTask = scanner.nextLine().split(splitMark);
                listOfShortTasks.add(new Task(tmpTask));
                tmpID = Integer.parseInt(tmpTask[0]);
                listIdOfShortTasks.add(tmpID);
                if (tmpID > tmpLastID) {
                    tmpLastID = tmpID;
                }
            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("failed to load the short task list");
            logger.warn(e.toString());

        }
        try {
            scanner = new Scanner(new File(pathListOfLongTasks));
            listOfLongTasks.clear();
            while (scanner.hasNextLine()){
                tmpTask = scanner.nextLine().split(splitMark);
                listOfLongTasks.add(new Task(tmpTask));
                tmpID = Integer.parseInt(tmpTask[0]);
                listIdOfLongTasks.add(tmpID);
                if (tmpID > tmpLastID) {
                    tmpLastID = tmpID;
                }
            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("failed to load the long task list");
            logger.warn(e.toString());
        }
        try {
            scanner = new Scanner(new File(pathListOfCompletedTasks));
            listOfCompletedTasks.clear();
            while (scanner.hasNextLine()){
                tmpTask = scanner.nextLine().split(splitMark);
                listOfCompletedTasks.add(new Task(tmpTask));
                tmpID = Integer.parseInt(tmpTask[0]);
                listIdOfCompletedTasks.add(tmpID);
                if (tmpID > tmpLastID) {
                    tmpLastID = tmpID;
                }
            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("failed to load the completed task list");
            logger.warn(e.toString());
        }
        lastID = tmpLastID;
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

    public boolean markAsCompleted(int ID, String userName) {
        if (listIdOfShortTasks.contains(ID)) {
            int index = listIdOfShortTasks.indexOf(ID);
            listOfShortTasks.get(index).setEditDate(new Date());
            listOfShortTasks.get(index).setEditedBy(userName);
            listOfCompletedTasks.add(listOfShortTasks.get(index));
            listIdOfCompletedTasks.add(ID);
            listOfShortTasks.remove(index);
            listIdOfShortTasks.remove(index);
            for (int i = 0; i < listToDo.size(); i++) {
                if (listToDo.get(i).getID() == ID) {
                    index = i;
                    break;
                }
            }
            listToDo.remove(index);
            return true;
        }
        if (listIdOfLongTasks.contains(ID)) {
            int index = listIdOfLongTasks.indexOf(ID);
            listOfLongTasks.get(index).setEditDate(new Date());
            listOfLongTasks.get(index).setEditedBy(userName);
            listOfCompletedTasks.add(listOfLongTasks.get(index));
            listIdOfCompletedTasks.add(ID);
            listOfLongTasks.remove(index);
            listIdOfLongTasks.remove(index);
            for (int i = 0; i < listToDo.size(); i++) {
                if (listToDo.get(i).getID() == ID) {
                    index = i;
                    break;
                }
            }
            listToDo.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Task> getSomeShortTasks(int startIndex, int someShortTasks) {
        ArrayList<Task> tmp = new ArrayList<>();
        int endIndex = listOfShortTasks.size();
        if(endIndex > someShortTasks) {
            endIndex = someShortTasks;
        }
        for (int i = startIndex; i < endIndex; i++ ) {
            tmp.add(getListOfShortTasks().get(i));
        }
        return tmp;
    }

    public Task getFirstLongTask(){
        return getListOfLongTasks().get(0);
    }

    public void sortTasksByPriority() {
        Task[] tmpTasks;

        tmpTasks = new Task[listOfShortTasks.size()];
        for (int i = 0; i < tmpTasks.length; i++){
            tmpTasks[i] = listOfShortTasks.get(i);
        }
        Arrays.sort(tmpTasks);
        listOfShortTasks.clear();
        listOfShortTasks.addAll(Arrays.asList(tmpTasks));
        listIdOfShortTasks.clear();
        for (Task t : listOfShortTasks) {
            listIdOfShortTasks.add(t.getID());
        }

        tmpTasks = new Task[listOfLongTasks.size()];
        for (int i = 0; i < tmpTasks.length; i++){
            tmpTasks[i] = listOfLongTasks.get(i);
        }
        Arrays.sort(tmpTasks);
        listOfLongTasks.clear();
        listOfLongTasks.addAll(Arrays.asList(tmpTasks));
        listIdOfLongTasks.clear();
        for (Task t : listOfLongTasks) {
            listIdOfLongTasks.add(t.getID());
        }
    }

    public void printTaskToDo(int endedWorkPhases, boolean previousCompleted, int tasksToAdd) {
        int someShortTasks = 4;
        if (previousCompleted) {
            listToDo.clear();

            System.out.print("you should work on ");
            if (endedWorkPhases % 2 == 0 && listOfShortTasks.size() > 0) {
                listToDo = getSomeShortTasks(0,someShortTasks);
                if (listToDo.size() > 1) {
                    System.out.println("that short tasks:");
                } else {
                    System.out.println("that short task:");
                }
            } else {
                listToDo.add(getFirstLongTask());
                System.out.println("that long task:");
            }
        } else {
            System.out.println("you should continue to work on:");
            if (listToDo.get(0).isShortTask() && listToDo.size() < someShortTasks && tasksToAdd > 0) {
                listToDo.addAll(getSomeShortTasks(listToDo.size(), tasksToAdd + listToDo.size()));
            }
        }
        if(listToDo.size() > 0) {
            for (Task task : listToDo) {
                task.printTask();
            }
        } else {
            System.out.println("no data");
        }
    }
}
