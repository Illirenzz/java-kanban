package app.model;

import org.junit.jupiter.api.Test;
import yandex.app.model.SubTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTaskTest {

    /**
     * по тз equals должен сравнивать только поле id и игнорировать другие поля
     */
    @Test
    void testEquals() {
        SubTask subTask1 = new SubTask("task1", "firstTask", 1);
        SubTask subTask2 = new SubTask("task2", "secondTask", 0);
        subTask2.setId(1);
        subTask1.setId(1);

        assertEquals(subTask1.getId(), subTask2.getId());
        //assertEquals(task1.getName(), task2.getName());
        //assertEquals(task1.getDescription(), task2.getDescription());
    }

}