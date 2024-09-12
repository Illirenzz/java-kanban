package app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import yandex.app.model.Task;
import yandex.app.service.HistoryManager;
import yandex.app.service.Managers;
import yandex.app.service.TaskManager;

import java.util.List;

class ManagersTest {

    @Test
    void getDefaultHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        List<Task> history = historyManager.getHistory();
        Assertions.assertNotNull(history);
    }

    /**
     * проверки объектов менеджера на null
     */
    @Test
    void getDefault() {
        TaskManager taskManager = Managers.getDefault();
        Assertions.assertNotNull(taskManager.getEpicTasks());
        Assertions.assertNotNull(taskManager.getTasks());
        Assertions.assertNotNull(taskManager.getSubTasks());
        Assertions.assertNotNull(taskManager.getHistory());
    }
}