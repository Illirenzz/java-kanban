package yandex.app.service;

import yandex.app.model.EpicTask;
import yandex.app.model.Status;
import yandex.app.model.SubTask;
import yandex.app.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, SubTask> subTasks;
    private final HashMap<Integer, EpicTask> epicTasks;

    private final HistoryManager inMemoryHistoryManager;
    private int id = 1;

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        subTasks = new HashMap<>();
        epicTasks = new HashMap<>();
        inMemoryHistoryManager = Managers.getDefaultHistory();
    }

    @Override
    public Task create(Task task) {
        task.setId(id++);
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public SubTask create(SubTask subTask) {
        subTask.setId(id++);
        subTasks.put(subTask.getId(), subTask);
        EpicTask epicTask = epicTasks.get(subTask.getEpicID());
        epicTask.addNewSubTask(subTask.getId());
        updateEpicStatus(epicTask);
        return subTask;
    }

    @Override
    public EpicTask create(EpicTask epicTask) {
        epicTask.setId(id++);
        epicTasks.put(epicTask.getId(), epicTask);
        return epicTask;
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        final EpicTask epicTask = epicTasks.get(subTask.getEpicID());
        if (epicTask == null) {
            return;
        }
        subTasks.put(subTask.getId(), subTask);
        updateEpicStatus(epicTask);
    }

    @Override
    public void updateEpicTask(EpicTask epicTask) {
        EpicTask save = epicTasks.get(epicTask.getId());
        if (save == null) {
            return;
        }
        save.setName(epicTask.getName());
        save.setDescription(epicTask.getDescription());
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public List<EpicTask> getEpicTasks() {
        return new ArrayList<>(epicTasks.values());
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void removeAllSubTasks() {
        subTasks.clear();
        epicTasks.forEach((k, v) -> {
            v.getSubTaskIDs().clear();
            updateEpicStatus(v);
        });
    }

    @Override
    public void removeAllEpicTasks() {
        epicTasks.clear();
        subTasks.clear();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        inMemoryHistoryManager.add(task);
        return task;
    }

    @Override
    public SubTask getSubTaskById(int id) {
        SubTask subTask = subTasks.get(id);
        inMemoryHistoryManager.add(subTask);
        return subTask;
    }

    @Override
    public EpicTask getEpicTaskById(int id) {
        EpicTask epicTask = epicTasks.get(id);
        inMemoryHistoryManager.add(epicTask);
        return epicTask;
    }

    @Override
    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    @Override
    public void removeSubTaskById(Integer id) {
        final SubTask subTask = subTasks.remove(id);
        final EpicTask epicTask = epicTasks.get(subTask.getEpicID());
        epicTask.removeSubTaskById(id);
        updateEpicStatus(epicTask);
    }

    @Override
    public void removeEpicTasksById(Integer id) {
        List<Integer> subTaskList = epicTasks.get(id).getSubTaskIDs();

        for (int subtaskId : subTaskList) {
            subTasks.remove(subtaskId);
        }

        epicTasks.remove(id);
    }

    @Override
    public List<SubTask> getEpicSubTaskById(Integer id) {
        List<Integer> subs = epicTasks.get(id).getSubTaskIDs();
        List<SubTask> result = new ArrayList<>();
        for (Integer subTaskId : subs) {
            result.add(subTasks.get(subTaskId));
        }
        return result;
    }

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

    @Override
    public List<Task> getHistory() {
        return inMemoryHistoryManager.getHistory();
    }

}