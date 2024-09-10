package yandex.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubTaskTest {

    @Test
    void testEquals() {
        SubTask subTask = new SubTask("task1", "firstTask", 1);
        SubTask subTask1 = new SubTask("task2", "secondTask", 0);
        subTask.setId(1);
        subTask1.setId(1);
        Assertions.assertEquals(subTask1, subTask);
    }

    //пока не придумал
    @Test
    void setEpicTaskTest() {

    }

}