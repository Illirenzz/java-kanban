package app;

import model.EpicTask;
import model.Status;
import model.SubTask;
import model.Task;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        //сначала создаем объект, потом вызываем create taskManager-a, затем добавляем через метод addNewSub(для подзадач)
        TaskManager tm = new TaskManager();
        Task task1 = new Task("задача1", "прост");
        Task task2 = new Task("задача2", "непрост");
        tm.create(task1);
        tm.create(task2);

        EpicTask epicTask1 = new EpicTask("огого задача", "ого как много работы");
        tm.create(epicTask1);

        SubTask subTask1 = new SubTask("микро задача", "да че тут делать то", epicTask1.getId());
        SubTask subTask2 = new SubTask("нано задача", "раз два и готово", epicTask1.getId());
        tm.create(subTask1);
        tm.create(subTask2);

        epicTask1.addNewSubTask(subTask1.getId());
        epicTask1.addNewSubTask(subTask2.getId());

        EpicTask epicTask2 = new EpicTask("работать работу", "Ты должен делать то, что должен делать.");
        tm.create(epicTask2);
        SubTask subTask3 = new SubTask("работа не волк", "в лес не убежит", epicTask2.getId());
        tm.create(subTask3);
        epicTask2.addNewSubTask(subTask3.getId());

        for (Task task : tm.getTasks()) {
            System.out.println(task);
        }
        for (EpicTask epicTask : tm.getEpicTasks()) {
            System.out.println(epicTask);
        }
        for (SubTask subTask : tm.getSubTasks()) {
            System.out.println(subTask);
        }

        task1.setStatus(Status.IN_PROGRESS);
        task2.setStatus(Status.DONE);
        subTask1.setStatus(Status.IN_PROGRESS);
        tm.updateTask(task1);
        tm.updateTask(task1);
        tm.updateSubTask(subTask1);
        System.out.println();
        System.out.println();

        for (Task task : tm.getTasks()) {
            System.out.println(task);
        }
        for (EpicTask epicTask : tm.getEpicTasks()) {
            System.out.println(epicTask);
        }
        for (SubTask subTask : tm.getSubTasks()) {
            System.out.println(subTask);
        }

        tm.removeEpicTasksById(epicTask2.getId());
        System.out.println();
        System.out.println();

        for (Task task : tm.getTasks()) {
            System.out.println(task);
        }
        for (EpicTask epicTask : tm.getEpicTasks()) {
            System.out.println(epicTask);
        }
        for (SubTask subTask : tm.getSubTasks()) {
            System.out.println(subTask);
        }
    }
}
