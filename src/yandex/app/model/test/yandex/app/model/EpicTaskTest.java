package yandex.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EpicTaskTest {
    @Test
    void testEquals() {
        EpicTask epicTask1 = new EpicTask("task1", "firstTask");
        EpicTask epicTask2 = new EpicTask("task2", "secondTask");
        epicTask1.setId(1);
        epicTask2.setId(1);
        Assertions.assertEquals(epicTask1, epicTask2);
    }

    //пока не придумал
    @Test
    void testAddNewSubTask() {

    }

}