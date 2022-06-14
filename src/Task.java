import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int ID;
    private String name;
    private boolean shortTask;
    private int priority;       // 1-2-3
    private String creationDate;
    private String createdBy;
    private String editDate;
    private String editedBy;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss zzz");

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShortTask() {
        return shortTask;
    }

    public void setShortTask(boolean shortTask) {
        this.shortTask = shortTask;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = sdf.format(creationDate);
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Task(String[] taskTable) throws ParseException {
        this.ID = Integer.parseInt(taskTable[0]);
        this.name = taskTable[1];
        this.shortTask = Boolean.parseBoolean(taskTable[2]);
        this.priority = Integer.parseInt(taskTable[3]);
        this.creationDate = taskTable[4];
        this.createdBy = taskTable[5];
        this.editDate = taskTable[6];
        this.editedBy = taskTable[7];
    }

    public Task(int ID, boolean shortTask, int priority) {
        this.ID = ID;
        this.name = "test task no " + ID;
        this.shortTask = shortTask;
        this.priority = priority;
        this.creationDate = sdf.format(new Date());
        this.createdBy = "Bartek";
        this.editDate = creationDate;
        this.editedBy = createdBy;
    }

    public void printTask() {
        System.out.println("task no: "+ID
                +" | name: "+name
                +" | short task: "+shortTask
                +" | priority: "+priority);
    }
}
