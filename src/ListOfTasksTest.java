import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ListOfTasksTest {

    @Test
    void loadTasksLists() throws FileNotFoundException, ParseException {
        ListOfTasks.loadTasksLists();

        System.out.println("short");
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfShortTasks());
        System.out.println("long");
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfLongTasks());
        System.out.println("completed");
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfCompletedTasks());

        ListOfTasks.markAsCompleted(2);
        ListOfTasks.markAsCompleted(5);

        System.out.println("short");
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfShortTasks());
        System.out.println("long");
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfLongTasks());
        System.out.println("completed");
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfCompletedTasks());

        ListOfTasks.saveTasksLists();
    }

    @Test
    void saveTasksLists() throws FileNotFoundException {
        ListOfTasks.addTaskToList(new Task(1, 5, 3));
        ListOfTasks.addTaskToList(new Task(2, 15, 2));
        ListOfTasks.addTaskToList(new Task(3, 30, 3));
        ListOfTasks.addTaskToList(new Task(4, 10, 1));
        ListOfTasks.addTaskToList(new Task(5, 45, 3));
        
        ListOfTasks.saveTasksLists();

        ListOfTasks.printListOfTasks(ListOfTasks.getListOfShortTasks());
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfLongTasks());
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfCompletedTasks());

//        ListOfTasks.loadTasksLists();
//        ListOfTasks.printListOfTasks(ListOfTasks.getListOfShortTasks());
    }

    @Test
    void test() {
        ListOfTasks.addTaskToList(new Task(7, 5, 3));
        ListOfTasks.addTaskToList(new Task(9, 15, 2));
        ListOfTasks.addTaskToList(new Task(13, 20, 3));
        ListOfTasks.addTaskToList(new Task(1, 10, 1));
        ListOfTasks.addTaskToList(new Task(5, 15, 3));

        ListOfTasks.sortTasks();

    }
}