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
        ListOfTasks.addTaskToList(new Task(7, true, 1));
        ListOfTasks.addTaskToList(new Task(9, true, 2));
        ListOfTasks.addTaskToList(new Task(13, true, 3));
        ListOfTasks.addTaskToList(new Task(1, true, 1));
        ListOfTasks.addTaskToList(new Task(5, true, 3));

        ListOfTasks.addTaskToList(new Task(5, false, 1));
        ListOfTasks.addTaskToList(new Task(6, false, 2));
        ListOfTasks.addTaskToList(new Task(2, false, 3));
        ListOfTasks.addTaskToList(new Task(8, false, 1));
        ListOfTasks.addTaskToList(new Task(20, false, 3));
        
        ListOfTasks.saveTasksLists();

//        ListOfTasks.printListOfTasks(ListOfTasks.getListOfShortTasks());
//        ListOfTasks.printListOfTasks(ListOfTasks.getListOfLongTasks());
//        ListOfTasks.printListOfTasks(ListOfTasks.getListOfCompletedTasks());

//        ListOfTasks.loadTasksLists();
//        ListOfTasks.printListOfTasks(ListOfTasks.getListOfShortTasks());
    }

    @Test
    void test() {
        ListOfTasks.addTaskToList(new Task(7, true, 1));
        ListOfTasks.addTaskToList(new Task(9, true, 2));
        ListOfTasks.addTaskToList(new Task(13, true, 3));
        ListOfTasks.addTaskToList(new Task(1, true, 1));
        ListOfTasks.addTaskToList(new Task(5, true, 3));

        ListOfTasks.addTaskToList(new Task(5, false, 1));
        ListOfTasks.addTaskToList(new Task(6, false, 2));
        ListOfTasks.addTaskToList(new Task(2, false, 3));
        ListOfTasks.addTaskToList(new Task(8, false, 1));
        ListOfTasks.addTaskToList(new Task(20, false, 3));

        ListOfTasks.printListOfTasks(ListOfTasks.getListOfShortTasks());
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfLongTasks());
        ListOfTasks.sortTasksByPriority();
        System.out.println("sorted");
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfShortTasks());
        ListOfTasks.printListOfTasks(ListOfTasks.getListOfLongTasks());
    }
}