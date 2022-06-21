import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ListOfTasks {
    private static final Logger logger = LogManager.getLogger(ListOfTasks.class);

    private ArrayList<Task> listToDo = new ArrayList<>();
    private ArrayList<Task> listOfShortTasks = new ArrayList<>();
    private ArrayList<Integer> listIdOfShortTasks = new ArrayList<>();
    private ArrayList<Task> listOfLongTasks = new ArrayList<>();
    private ArrayList<Integer> listIdOfLongTasks = new ArrayList<>();
    private ArrayList<Task> listOfCompletedTasks = new ArrayList<>();
    private  ArrayList<Integer> listIdOfCompletedTasks = new ArrayList<>();
    public int lastID = 0;

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
        logger.info("task has been added, task ID: " + task.getID());
        sortTasksByPriority();
    }

    public void loadTasksLists() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("tasks.dat"));
        listOfShortTasks = (ArrayList<Task>) objectInputStream.readObject();
        listOfLongTasks = (ArrayList<Task>) objectInputStream.readObject();
        listOfCompletedTasks = (ArrayList<Task>) objectInputStream.readObject();

        int tmpID;
        listIdOfShortTasks.clear();
        for (Task task : listOfShortTasks) {
            listIdOfShortTasks.add(task.getID());
            tmpID = task.getID();
            if (tmpID > lastID) {
                lastID = tmpID;
            }
        }
        listIdOfLongTasks.clear();
        for (Task task : listOfLongTasks) {
            listIdOfLongTasks.add(task.getID());
            tmpID = task.getID();
            if (tmpID > lastID) {
                lastID = tmpID;
            }
        }
        listIdOfCompletedTasks.clear();
        for (Task task : listOfCompletedTasks) {
            listIdOfCompletedTasks.add(task.getID());
            tmpID = task.getID();
            if (tmpID > lastID) {
                lastID = tmpID;
            }
        }
    }

    public void saveTasksLists() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("tasks.dat"));
        objectOutputStream.writeObject(listOfShortTasks);
        objectOutputStream.writeObject(listOfLongTasks);
        objectOutputStream.writeObject(listOfCompletedTasks);
        objectOutputStream.close();
        logger.info("tasks have been saved");
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

            for (Task task : listToDo) {
                if (task.getID() == ID) {
                    listToDo.remove(task);
                    break;
                }
            }
            logger.info("task marked as done, task ID: " + ID);
            return true;
        }
        else if (listIdOfLongTasks.contains(ID)) {
            int index = listIdOfLongTasks.indexOf(ID);
            listOfLongTasks.get(index).setEditDate(new Date());
            listOfLongTasks.get(index).setEditedBy(userName);
            listOfCompletedTasks.add(listOfLongTasks.get(index));
            listIdOfCompletedTasks.add(ID);
            listOfLongTasks.remove(index);
            listIdOfLongTasks.remove(index);

            for (Task task : listToDo) {
                if (task.getID() == ID) {
                    listToDo.remove(task);
                    break;
                }
            }
            logger.info("task marked as done, task ID: " + ID);
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
        if (getListOfLongTasks().size() > 0) {
            return getListOfLongTasks().get(0);
        }
        return null;
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
        logger.info("tasks was sorted");
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

    public void printTaskToDo(int endedWorkPhases, boolean previousCompleted, int tasksToAdd) {
        int someShortTasks = 4;
        if (previousCompleted) {
            listToDo.clear();
            System.out.print("you should work on: ");
            if (endedWorkPhases % 2 == 0 && listOfShortTasks.size() > 0) {
                listToDo = getSomeShortTasks(0,someShortTasks);
                if (listToDo.size() > 1) {
                    System.out.println("that short tasks:");
                } else {
                    System.out.println("that short task:");
                }
            } else {
                if (getFirstLongTask() != null) {
                    listToDo.add(getFirstLongTask());
                    System.out.println("that long task:");
                }
            }
        } else {
            if (listToDo.get(0).isShortTask() && listToDo.size() < someShortTasks && tasksToAdd > 0) {
                System.out.println("you should work on:");
                listToDo.addAll(getSomeShortTasks(listToDo.size(), tasksToAdd + listToDo.size()));
            }
        }
        if(listToDo.size() > 0) {
            for (Task task : listToDo) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println("no data");
        }
    }
}
