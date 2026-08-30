public class Task {

    private String taskName;
    private String taskDescription;
    private boolean taskStatus;

    public Task(String taskName, String taskDescription, boolean taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }
    public String getTaskName() {
        return taskName;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void markComplete(){
        taskStatus = true;
    }
    public void unmarkComplete(){
        taskStatus = false;
    }

    @Override
    public String toString() {
        return ("Task name: " + taskName + "\nDescription: " + taskDescription + "\nStatus: " + (taskStatus ? "DONE" : "NOT DONE"));
    }
}
