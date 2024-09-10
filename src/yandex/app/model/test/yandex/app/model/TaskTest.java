package yandex.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testEquals() {
        Task task1 = new Task("task1", "firstTask");
        Task task2 = new Task("task2", "secondTask");
        task1.setId(1);
        task2.setId(1);
        Assertions.assertEquals(task1, task2);
    }
}