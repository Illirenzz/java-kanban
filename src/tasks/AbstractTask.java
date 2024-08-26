package tasks;

import java.util.Objects;

public class AbstractTask {
    private final String name;
    private final String description;
    Status status;
    private int id;

    public AbstractTask(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = Status.NEW;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractTask task)) return false;
        return id == task.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
