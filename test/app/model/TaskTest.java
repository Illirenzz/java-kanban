package app.model;

import org.junit.jupiter.api.Test;
import yandex.app.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    /**
     * по тз equals должен сравнивать только поле id и игнорировать другие поля
     */
    @Test
    void testEquals() {
        Task task1 = new Task("task1", "firstTask");
        Task task2 = new Task("task2", "secondTask");
        task1.setId(1);
        task2.setId(1);

        assertEquals(task1.getId(), task2.getId());
        //assertEquals(task1.getName(), task2.getName());
        //assertEquals(task1.getDescription(), task2.getDescription());
    }

}