import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Random;

class ListOfTasksTest {
    ListOfTasks listOfTasks = new ListOfTasks();

    void makeTaskList(int quantity, int msSleep) throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < quantity; i++){
            listOfTasks.addTaskToList(new Task(i+1,"task #" + (i + 1),
                    random.nextBoolean(), random.nextInt(1,4), "test"));
            Thread.sleep(msSleep);
        }
    }

    @Test
    void loadTasksLists() throws IOException, ParseException, ClassNotFoundException {
        listOfTasks.loadTasksLists();

        listOfTasks.markAsCompleted(2,"test");
        listOfTasks.markAsCompleted(5,"test");

        System.out.println("short");
        listOfTasks.printListOfTasks(listOfTasks.getListOfShortTasks());
        System.out.println("long");
        listOfTasks.printListOfTasks(listOfTasks.getListOfLongTasks());
        System.out.println("completed");
        listOfTasks.printListOfTasks(listOfTasks.getListOfCompletedTasks());

        listOfTasks.saveTasksLists();
    }

    @Test
    void saveTasksLists() throws IOException, InterruptedException {;
        makeTaskList(30, 0);
        listOfTasks.saveTasksLists();
    }

//    @Test
//    void sortTasksByPriority() throws FileNotFoundException, ParseException {
//        listOfTasks.loadTasksLists();
//        listOfTasks.sortTasksByPriority();
//
//        System.out.println("short");
//        listOfTasks.printListOfTasks(listOfTasks.getListOfShortTasks());
//        System.out.println("long");
//        listOfTasks.printListOfTasks(listOfTasks.getListOfLongTasks());
//        System.out.println("completed");
//        listOfTasks.printListOfTasks(listOfTasks.getListOfCompletedTasks());
//    }

    @Test
    void sortTasksByPriority2() throws IOException, ParseException, ClassNotFoundException {
        listOfTasks.loadTasksLists();
        listOfTasks.sortTasksByPriority();

        System.out.println("short");
        listOfTasks.printListOfTasks(listOfTasks.getListOfShortTasks());
        System.out.println("long");
        listOfTasks.printListOfTasks(listOfTasks.getListOfLongTasks());
        System.out.println("completed");
        listOfTasks.printListOfTasks(listOfTasks.getListOfCompletedTasks());
    }

//    @Test
//    void test() throws FileNotFoundException, ParseException {
//        listOfTasks.loadTasksLists();
//        listOfTasks.printTaskToDo(2,false);
//        listOfTasks.printTaskToDo(3, false);
//    }
}