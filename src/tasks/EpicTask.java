package tasks;

import java.util.ArrayList;
import java.util.List;

public class EpicTask extends Task {
    private final List<SubTask> subTaskList;

    public EpicTask(String name, String description) {
        super(name, description);
        subTaskList = new ArrayList<>();
    }

    public void addNewSubTask(SubTask subTask) {
        subTaskList.add(subTask);
    }

    public List<SubTask> getSubTaskList() {
        return subTaskList;
    }

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\b,subTaskList=" + subTaskList +
                '}';
    }

    public boolean isAllSubDone() {
        for (SubTask subTask : subTaskList) {
            if (subTask.getStatus() != Status.DONE) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllSubNew() {
        for (SubTask subTask : subTaskList) {
            if (subTask.getStatus() != Status.NEW) {
                return false;
            }
        }
        return true;
    }
}
