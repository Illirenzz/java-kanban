package app.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yandex.app.model.Task;
import yandex.app.service.Managers;
import yandex.app.service.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Менеджер истории просмотров")
class InMemoryHistoryManagerTest {

    @Test
    void shouldStoreOnly10Values() {
        TaskManager taskManager = Managers.getDefault();

        for (int i = 0; i < 15; i++) {
            Task task = new Task("task" + i, String.valueOf(i));
            taskManager.create(task);
            taskManager.getTaskById(task.getId());
        }

        assertEquals(10, taskManager.getHistory().size());
    }
}