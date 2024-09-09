package yandex.app;

import yandex.app.model.EpicTask;
import yandex.app.model.Status;
import yandex.app.model.SubTask;
import yandex.app.model.Task;
import yandex.app.service.InMemoryTaskManager;
import yandex.app.service.TaskManager;

public class Main {
    public static void main(String[] args) {
        //сначала создаем объект, потом вызываем create taskManager-a, затем добавляем через метод addNewSub(для подзадач)
        //тесты с 4 спринта
        InMemoryTaskManager inMemoryTM = new InMemoryTaskManager();
        Task task1 = new Task("задача1", "прост");
        Task task2 = new Task("задача2", "непрост");
        inMemoryTM.create(task1);
        inMemoryTM.create(task2);

        EpicTask epicTask1 = new EpicTask("огого задача", "ого как много работы");
        inMemoryTM.create(epicTask1);

        SubTask subTask1 = new SubTask("микро задача", "да че тут делать то", epicTask1.getId());
        SubTask subTask2 = new SubTask("нано задача", "раз два и готово", epicTask1.getId());
        inMemoryTM.create(subTask1);
        inMemoryTM.create(subTask2);


        EpicTask epicTask2 = new EpicTask("работать работу", "Ты должен делать то, что должен делать.");
        inMemoryTM.create(epicTask2);
        SubTask subTask3 = new SubTask("работа не волк", "в лес не убежит", epicTask2.getId());
        inMemoryTM.create(subTask3);

        for (Task task : inMemoryTM.getTasks()) {
            System.out.println(task);
        }
        for (EpicTask epicTask : inMemoryTM.getEpicTasks()) {
            System.out.println(epicTask);
        }
        for (SubTask subTask : inMemoryTM.getSubTasks()) {
            System.out.println(subTask);
        }

        task1.setStatus(Status.IN_PROGRESS);
        task2.setStatus(Status.DONE);
        subTask1.setStatus(Status.IN_PROGRESS);
        inMemoryTM.updateTask(task1);
        inMemoryTM.updateTask(task1);
        inMemoryTM.updateSubTask(subTask1);
        System.out.println();
        System.out.println();

        for (Task task : inMemoryTM.getTasks()) {
            System.out.println(task);
        }
        for (EpicTask epicTask : inMemoryTM.getEpicTasks()) {
            System.out.println(epicTask);
        }
        for (SubTask subTask : inMemoryTM.getSubTasks()) {
            System.out.println(subTask);
        }

        inMemoryTM.removeEpicTasksById(epicTask2.getId());
        System.out.println();
        System.out.println();

        for (Task task : inMemoryTM.getTasks()) {
            System.out.println(task);
        }
        for (EpicTask epicTask : inMemoryTM.getEpicTasks()) {
            System.out.println(epicTask);
        }
        for (SubTask subTask : inMemoryTM.getSubTasks()) {
            System.out.println(subTask);
        }

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();
        printAllTasks(inMemoryTM);
    }


    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpicTasks()) {
            System.out.println(epic);

            for (Task task : manager.getEpicSubTaskById(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubTasks()) {
            System.out.println(subtask);
        }

        manager.getTaskById(1);
        manager.getSubTaskById(5);

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
