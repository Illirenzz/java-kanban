package yandex.app.service;

import yandex.app.model.EpicTask;
import yandex.app.model.Status;
import yandex.app.model.SubTask;
import yandex.app.model.Task;

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
        EpicTask epicTask = epicTasks.get(subTask.getEpicID());
        epicTask.addNewSubTask(subTask.getId());
        updateEpicStatus(epicTask);
        return subTask;
    }

    public EpicTask create(EpicTask epicTask) {
        epicTask.setId(id++);
        epicTasks.put(epicTask.getId(), epicTask);
        return epicTask;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateSubTask(SubTask subTask) {
        final EpicTask epicTask = epicTasks.get(subTask.getEpicID());
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
        epicTasks.forEach((k, v) -> {
            v.getSubTaskIDs().clear();
            updateEpicStatus(v);
        });
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

    public void removeSubTaskById(Integer id) {
        final SubTask subTask = subTasks.remove(id);
        final EpicTask epicTask = epicTasks.get(subTask.getEpicID());
        epicTask.removeSubTaskById(id);
        updateEpicStatus(epicTask);
    }

    public void removeEpicTasksById(Integer id) {
        List<Integer> subTaskList = epicTasks.get(id).getSubTaskIDs();

        for (int subtaskId : subTaskList) {
            subTasks.remove(subtaskId);
        }

        epicTasks.remove(id);
    }

    public List<SubTask> getAllEpicSubtaskById(Integer id) {
        List<Integer> subs = epicTasks.get(id).getSubTaskIDs();
        List<SubTask> result = new ArrayList<>();
        for (Integer subTaskId : subs) {
            result.add(subTasks.get(subTaskId));
        }
        return result;
    }

    //обновляется при изменении подзадачи, можно менять только через методы обновления и удаления подзадач
    private void updateEpicStatus(EpicTask epicTask) {
        Status status = null;
        List<SubTask> subTaskList = new ArrayList<>();

        int isDone = 0;
        int isNew = 0;
        for (Integer subTaskId : epicTask.getSubTaskIDs()) {
            SubTask tmpSubtaskForCalculate = subTasks.get(subTaskId);
            subTaskList.add(tmpSubtaskForCalculate);
            if (tmpSubtaskForCalculate.getStatus() == Status.DONE) {
                isDone++;
            } else if (tmpSubtaskForCalculate.getStatus() == Status.NEW) {
                isNew++;
            } else {
                epicTask.setStatus(Status.IN_PROGRESS);
                return;
            }
        }

        if (isNew == subTaskList.size()) {
            status = Status.NEW;
        } else if (isDone == subTaskList.size()) {
            status = Status.DONE;
        } else {
            status = Status.IN_PROGRESS;
        }
        epicTask.setStatus(status);
    }
}