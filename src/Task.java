import java.util.ArrayList;
import java.util.Date;

public class Task {
    private int ID;
    private String name;
    private int estimatedTime;
    private int priority;
    private Date creationDate;
    private String createdBy;
    private Date editDate;
    private String editedBy;

    private ArrayList<Task> listOfShortTasks = new ArrayList<>();
    private ArrayList<Task> listOfLongTasks = new ArrayList<>();

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

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public Task() {
    }

    public Task(int ID, String name, int estimatedTime, int priority, Date creationDate, String createdBy, Date editDate, String editedBy) {
        this.ID = ID;
        this.name = name;
        this.estimatedTime = estimatedTime;
        this.priority = priority;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.editDate = editDate;
        this.editedBy = editedBy;
    }

    public Task(int ID, int estimatedTime, int priority) {
        this.ID = ID;
        this.estimatedTime = estimatedTime;
        this.priority = priority;

        listOfShortTasks.add(this);
    }
}
