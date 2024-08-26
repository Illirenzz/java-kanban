package tasks;

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

    public Task getTask(Task task) {

        task.setId(id++);
        tasks.put(task.getId(), task);
        return task;
    }

    public SubTask getSubTask(SubTask subTask) {
        subTask.setId(id++);
        subTasks.put(subTask.getId(), subTask);
        return subTask;
    }

    public EpicTask getEpicTask(EpicTask epicTask) {
        epicTask.setId(id++);
        epicTasks.put(epicTask.getId(), epicTask);
        return epicTask;
    }

    /**
     * записывает обновленную задачу в карту, если задача уровня эпик устанавливает ей подходящий статус
     * не знаю, правильно ли я понял. в параметр приходит прочитанная ранее и изменённая задача?
     * и мне нет необходимости отдельно переписывать название и описание, нужно только просто перезаписать её в мапу?
     *
     * @param task
     */
    public void updateTask(Task task) {
        if (task.getClass() == EpicTask.class) {
            EpicTask epicTask = (EpicTask) task;

            if (epicTask.isAllSubDone()) {
                epicTask.setStatus(Status.DONE);
                epicTasks.put(epicTask.getId(), epicTask);
            } else if (epicTask.isAllSubNew() || epicTask.getSubTaskList().isEmpty()) {
                epicTask.setStatus(Status.NEW);
                epicTasks.put(epicTask.getId(), epicTask);
            } else {
                epicTask.setStatus(Status.IN_PROGRESS);
                epicTasks.put(epicTask.getId(), epicTask);
            }

        } else if (task.getClass() == SubTask.class) {
            subTasks.put(task.getId(), (SubTask) task);
        } else {
            tasks.put(task.getId(), task);
        }
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
    }

    public void removeAllEpicTasks() {
        epicTasks.clear();
    }

    public Task getTaskById(int id) {
        Task result = null;
        result = tasks.get(id);
        return result;
    }

    public SubTask getSubTaskById(int id) {
        SubTask result = null;
        result = subTasks.get(id);
        return result;
    }

    public EpicTask getEpicTaskById(int id) {
        EpicTask result = null;
        result = epicTasks.get(id);
        return result;
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public void removeSubTaskById(int id) {
        subTasks.remove(id);
    }

    public void removeEpicTasksById(int id) {
        epicTasks.remove(id);
    }

    public List<SubTask> getAllEpicSubtaskById(int id) {
        return epicTasks.get(id).getSubTaskList();
    }
}