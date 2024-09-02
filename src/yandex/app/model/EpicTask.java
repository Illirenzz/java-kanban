package yandex.app.model;

import java.util.ArrayList;
import java.util.List;

public class EpicTask extends Task {
    private final List<Integer> subTaskIds;

    public EpicTask(String name, String description) {
        super(name, description);
        subTaskIds = new ArrayList<>();
    }


    public void addNewSubTask(int subTask) {
        subTaskIds.add(subTask);
    }

    public List<Integer> getSubTaskIDs() {
        return subTaskIds;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\b,subTaskList=" + subTaskIds +
                '}';
    }

    public void removeSubTaskById(Integer id) {
        subTaskIds.remove(id);
    }

}
