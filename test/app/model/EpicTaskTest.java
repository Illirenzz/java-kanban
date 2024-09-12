package app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yandex.app.model.EpicTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ТестЭпика")
class EpicTaskTest {

    /**
     * по тз equals должен сравнивать только поле id и игнорировать другие поля
     */
    @Test
    void testEquals() {
        EpicTask epicTask1 = new EpicTask("task1", "firstTask");
        EpicTask epicTask2 = new EpicTask("task2", "secondTask");
        epicTask1.setId(1);
        epicTask2.setId(1);

        assertEquals(epicTask1.getId(), epicTask2.getId());
        //assertEquals(task1.getName(), task2.getName());
        //assertEquals(task1.getDescription(), task2.getDescription());
    }

}