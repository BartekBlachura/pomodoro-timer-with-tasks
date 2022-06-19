import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable<Task>{
    private final int ID;
    private final String name;
    private final boolean shortTask;
    private final int priority;
    private final String creationDate;
    private final String createdBy;
    private String editDate;
    private String editedBy;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss zzz");

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public boolean isShortTask() {
        return shortTask;
    }

    public int getPriority() {
        return priority;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = sdf.format(editDate);
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public Task(int ID, String name, boolean shortTask, int priority, String createdBy) {
        this.ID = ID;
        this.name = name;
        this.shortTask = shortTask;
        this.priority = priority;
        this.creationDate = sdf.format(new Date());
        this.createdBy = createdBy;
        this.editDate = creationDate;
        this.editedBy = createdBy;
    }

    public Task(String[] taskTable) {
        this.ID = Integer.parseInt(taskTable[0]);
        this.name = taskTable[1];
        this.shortTask = Boolean.parseBoolean(taskTable[2]);
        this.priority = Integer.parseInt(taskTable[3]);
        this.creationDate = taskTable[4];
        this.createdBy = taskTable[5];
        this.editDate = taskTable[6];
        this.editedBy = taskTable[7];
    }

    public void printTask() {
        System.out.println("task no: "+ID
                +" | name: "+name.replace("_", " ")
                +" | short task: "+shortTask
                +" | priority: "+priority);
    }

    @Override
    public int compareTo(Task o) {
        if (this.priority > o.priority) {
            return -1;
        } else if (this.priority == o.priority) {
            if (this.ID < o.ID) {
                return -1;
            }
            else {
                return 1;
            }
        }
        else {
            return 1;
        }
    }
}
