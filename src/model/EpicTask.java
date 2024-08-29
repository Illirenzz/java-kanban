package model;

import java.util.ArrayList;
import java.util.List;

public class EpicTask extends Task {
    private final List<Integer> subTaskID;

    public EpicTask(String name, String description) {
        super(name, description);
        subTaskID = new ArrayList<>();
    }


    public void addNewSubTask(int subTask) {
        subTaskID.add(subTask);
    }

    public List<Integer> getSubTaskIDs() {
        return subTaskID;
    }

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\b,subTaskList=" + subTaskID +
                '}';
    }

}
