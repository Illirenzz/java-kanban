package tasks;

public class Task extends AbstractTask {
    public Task(String name, String description) {
        super(name, description);
    }

    void setStatus(Status status) {
        this.status = status;
    }
}
