package tasks;

public class Task extends AbstractTask {
    public Task(String name, String description) {
        super(name, description);
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
