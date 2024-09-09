package yandex.app.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yandex.app.model.Task;
import yandex.app.service.InMemoryTaskManager;

class TaskTest {
    InMemoryTaskManager inMemoryTaskManager;

    @BeforeEach
    public void clearManager() {
        inMemoryTaskManager = new InMemoryTaskManager();
    }

    //единственно важный, проверить равенство при сравнении
    @Test
    void testEquals() {
        Task task1 = new Task("task1", "firstTask");
        Task task2 = new Task("task2", "secondTask");
        task1.setId(1);
        task2.setId(1);


        Assertions.assertEquals(task1, task2);

    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getStatus() {
    }

    @Test
    void setStatus() {
    }
}