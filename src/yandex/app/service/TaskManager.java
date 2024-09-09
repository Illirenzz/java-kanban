package yandex.app.service;

import yandex.app.model.EpicTask;
import yandex.app.model.SubTask;
import yandex.app.model.Task;

import java.util.List;

public interface TaskManager {
    Task create(Task task);

    SubTask create(SubTask subTask);

    EpicTask create(EpicTask epicTask);

    void updateTask(Task task);

    void updateSubTask(SubTask subTask);

    void updateEpicTask(EpicTask epicTask);

    List<Task> getTasks();

    List<SubTask> getSubTasks();

    List<EpicTask> getEpicTasks();

    void removeAllTasks();

    void removeAllSubTasks();

    void removeAllEpicTasks();

    Task getTaskById(int id);

    SubTask getSubTaskById(int id);

    EpicTask getEpicTaskById(int id);

    void removeTaskById(int id);

    void removeSubTaskById(Integer id);

    void removeEpicTasksById(Integer id);

    List<SubTask> getEpicSubTaskById(Integer id);

    List<Task> getHistory();
}
