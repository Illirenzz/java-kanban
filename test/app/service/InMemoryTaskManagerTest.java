package app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yandex.app.model.EpicTask;
import yandex.app.model.SubTask;
import yandex.app.model.Task;
import yandex.app.service.Managers;
import yandex.app.service.TaskManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * в многих тестах проходит сравнение объектов с разным состоянием но одинаковым id через equals.
 * как я понял из тз, объекты с разными состояниями но одинаковым id должны быть идентичны по equals
 */
class InMemoryTaskManagerTest {
    static TaskManager taskManager;

    @BeforeEach
    void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    void createTask() {
        Task task1 = new Task("task1", "task");
        taskManager.create(task1);

        Assertions.assertNotNull(taskManager.getTaskById(task1.getId()));
        Assertions.assertEquals(task1, taskManager.getTaskById(task1.getId()));
    }

    @Test
    void testCreateEpicTask() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);

        Assertions.assertNotNull(taskManager.getEpicTaskById(epicTask.getId()));
        Assertions.assertEquals(epicTask, taskManager.getEpicTaskById(epicTask.getId()));
    }

    @Test
    void testCreateSubTask() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);
        SubTask subTask = new SubTask("task1", "task", epicTask.getId());
        taskManager.create(subTask);

        Assertions.assertNotNull(taskManager.getSubTaskById(subTask.getId()));
        Assertions.assertEquals(subTask, taskManager.getSubTaskById(subTask.getId()));
    }

    @Test
    void updateTask() {
        Task task1 = new Task("task1", "task");
        taskManager.create(task1);

        Task updatedTask1 = new Task("task1upd", "updatedTask1");
        updatedTask1.setId(task1.getId());
        taskManager.updateTask(updatedTask1);

        Assertions.assertEquals(updatedTask1, taskManager.getTaskById(task1.getId()));
        Assertions.assertEquals("updatedTask1", taskManager.getTaskById(task1.getId()).getDescription());
    }

    @Test
    void updateSubTask() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);

        SubTask subTask = new SubTask("subTask1", "task", epicTask.getId());
        taskManager.create(subTask);
        epicTask.addNewSubTask(subTask.getId());

        SubTask updatedSubTask1 = new SubTask("subTask1upd", "updatedSubTask1", subTask.getEpicID());
        updatedSubTask1.setId(subTask.getId());
        taskManager.updateSubTask(updatedSubTask1);

        Assertions.assertEquals(updatedSubTask1, taskManager.getSubTaskById(subTask.getId()));
        Assertions.assertEquals("updatedSubTask1", taskManager.getSubTaskById(subTask.getId()).getDescription());
    }

    @Test
    void updateEpicTask() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);
        EpicTask epicTaskUpd = new EpicTask("updatedEpicTask", "big task upd");
        epicTaskUpd.setId(epicTask.getId());

        taskManager.updateEpicTask(epicTaskUpd);

        Assertions.assertEquals(epicTaskUpd, taskManager.getEpicTaskById(epicTask.getId()));
        Assertions.assertEquals("big task upd", taskManager.getEpicTaskById(epicTask.getId()).getDescription());
    }

    @Test
    void removeAllTasks() {
        Task task1 = new Task("task1", "task");
        taskManager.create(task1);
        Assertions.assertEquals(task1, taskManager.getTaskById(task1.getId()));

        taskManager.removeAllTasks();

        Assertions.assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    void removeAllSubTasks() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);
        SubTask subTask = new SubTask("task1", "task", epicTask.getId());
        taskManager.create(subTask);

        Assertions.assertEquals(subTask, taskManager.getSubTaskById(subTask.getId()));

        taskManager.removeAllSubTasks();
        Assertions.assertEquals(0, taskManager.getSubTasks().size());
    }

    @Test
    void removeAllEpicTasks() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);
        SubTask subTask = new SubTask("task1", "task", epicTask.getId());
        taskManager.create(subTask);

        Assertions.assertEquals(epicTask, taskManager.getEpicTaskById(epicTask.getId()));

        taskManager.removeAllEpicTasks();

        Assertions.assertEquals(0, taskManager.getEpicTasks().size());
        Assertions.assertEquals(0, taskManager.getSubTasks().size());
    }

    @Test
    void getTaskById() {
        Task task1 = new Task("task1", "task");
        taskManager.create(task1);

        Task test = taskManager.getTaskById(task1.getId());

        Assertions.assertEquals(task1, test);
    }

    @Test
    void getSubTaskById() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);
        SubTask subTask = new SubTask("task1", "task", epicTask.getId());
        taskManager.create(subTask);

        SubTask test = taskManager.getSubTaskById(subTask.getId());
        assertEquals(subTask, test);
    }

    @Test
    void getEpicTaskById() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);

        EpicTask test = taskManager.getEpicTaskById(epicTask.getId());

        assertEquals(epicTask, test);
    }

    @Test
    void removeTaskById() {
        Task task1 = new Task("task1", "task");
        taskManager.create(task1);
        taskManager.removeTaskById(task1.getId());

        assertNull(taskManager.getTaskById(task1.getId()));
    }

    @Test
    void removeSubTaskById() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);
        SubTask subTask = new SubTask("task1", "task", epicTask.getId());
        taskManager.create(subTask);

        taskManager.removeSubTaskById(subTask.getId());
        assertNull(taskManager.getSubTaskById(subTask.getId()));
    }

    @Test
    void removeEpicTasksById() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);

        taskManager.removeEpicTasksById(epicTask.getId());
        assertNull(taskManager.getEpicTaskById(epicTask.getId()));
    }

    @Test
    void getEpicSubTaskById() {
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);
        SubTask subTask = new SubTask("task1", "task", epicTask.getId());
        taskManager.create(subTask);

        assertEquals(epicTask, taskManager.getEpicTaskById(epicTask.getId()));
    }

    @Test
    void getHistory() {
        //создаю таски и проверяю, что они сохраняются
        Task task1 = new Task("task1", "task");
        taskManager.create(task1);
        Task task2 = new Task("task2", "task2");
        taskManager.create(task2);
        EpicTask epicTask = new EpicTask("epicTask", "big task");
        taskManager.create(epicTask);
        SubTask subTask = new SubTask("subTask1", "subTask1", epicTask.getId());
        taskManager.create(subTask);

        taskManager.getTaskById(task1.getId());
        taskManager.getTaskById(task2.getId());
        taskManager.getEpicTaskById(epicTask.getId());
        taskManager.getSubTaskById(subTask.getId());

        List<Task> test = new ArrayList<>();
        test.add(task1);
        test.add(task2);
        test.add(epicTask);
        test.add(subTask);

        assertEquals(test, taskManager.getHistory());

        //обновляю таску и проверяю, чтобы и предыдущая, и новая версия сохранились в истории просмотров
        Task updatedTask = new Task("updatedTask1", "updatedTask1Description");
        updatedTask.setId(task1.getId());
        taskManager.updateTask(updatedTask);
        taskManager.getTaskById(updatedTask.getId());

        assertTrue(taskManager.getHistory().contains(task1));
        assertTrue(taskManager.getHistory().contains(updatedTask));

    }
}