package service;

import model.EpicTask;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, SubTask> subTasks;
    private final HashMap<Integer, EpicTask> epicTasks;
    private int id = 1;

    public TaskManager() {
        tasks = new HashMap<>();
        subTasks = new HashMap<>();
        epicTasks = new HashMap<>();
    }

    public Task create(Task task) {
        task.setId(id++);
        tasks.put(task.getId(), task);
        return task;
    }

    public SubTask create(SubTask subTask) {
        subTask.setId(id++);
        subTasks.put(subTask.getId(), subTask);
        updateEpicStatus(epicTasks.get(subTask.getEpicID()));
        return subTask;
    }

    public EpicTask create(EpicTask epicTask) {
        epicTask.setId(id++);
        epicTask.setStatus(Status.NEW);
        epicTasks.put(epicTask.getId(), epicTask);
        return epicTask;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateSubTask(SubTask subTask) {
        EpicTask epicTask = epicTasks.get(subTask.getEpicID());
        if (epicTask == null) {
            return;
        }
        subTasks.put(subTask.getId(), subTask);
        updateEpicStatus(epicTask);
    }

    public void updateEpicTask(EpicTask epicTask) {
        EpicTask save = epicTasks.get(epicTask.getId());
        if (save == null) {
            return;
        }
        save.setName(epicTask.getName());
        save.setDescription(epicTask.getDescription());
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public List<EpicTask> getEpicTasks() {
        return new ArrayList<>(epicTasks.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllSubTasks() {
        subTasks.clear();
        epicTasks.forEach((k, v) -> updateEpicTask(v));
    }

    public void removeAllEpicTasks() {
        epicTasks.clear();
        subTasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }

    public EpicTask getEpicTaskById(int id) {
        return epicTasks.get(id);
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeSubTaskById(int id) {
        updateEpicTask(epicTasks.get(subTasks.get(id).getEpicID()));
        subTasks.remove(id);
    }

    public void removeEpicTasksById(int id) {
        List<Integer> subTaskList = epicTasks.get(id).getSubTaskIDs();

        if (!subTaskList.isEmpty()) {
            for (int i : subTaskList) {
                subTasks.remove(i);
            }
        }
        epicTasks.remove(id);
    }

    public List<SubTask> getAllEpicSubtaskById(int id) {
        List<Integer> subs = epicTasks.get(id).getSubTaskIDs();
        List<SubTask> result = new ArrayList<>();
        for (Integer i : subs) {
            result.add(subTasks.get(i));
        }
        return result;
    }

    //обновляется при изменении подзадачи, можно менять только через методы обновления и удаления подзадач
    private void updateEpicStatus(EpicTask epicTask) {
        Status status = null;
        List<SubTask> subTaskList = new ArrayList<>();

        for (Integer i : epicTask.getSubTaskIDs()) {
            subTaskList.add(subTasks.get(i));
        }
        boolean isAllNew = subTaskList.stream().allMatch(x -> x.getStatus() == Status.NEW);
        boolean isAllDone = subTaskList.stream().allMatch(x -> x.getStatus() == Status.DONE);

        if (subTaskList.isEmpty() || isAllNew) {
            status = Status.NEW;
        } else if (isAllDone) {
            status = Status.DONE;
        } else {
            status = Status.IN_PROGRESS;
        }
        epicTask.setStatus(status);
    }
}