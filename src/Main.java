import tasks.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
    }

}

       /* EpicTask epicTask = taskManager.getEpicTask(new EpicTask("Поездка", "Сьездить на кладбище"));
        SubTask subTask1 = taskManager.getSubTask(new SubTask("Собрать вещи", "мешки для мусора, перчатки, воду", epicTask.getId()));
        SubTask subTask2 = taskManager.getSubTask(new SubTask("Приехать", "сначала приехать до остановки кладбищенского автобуса, потом до самого кладбища", epicTask.getId()));
        SubTask subTask3 = taskManager.getSubTask(new SubTask("прибраться", "убрать мусор, листья, помыть посуду", epicTask.getId()));
        SubTask subTask4 = taskManager.getSubTask(new SubTask("уехать", "доехать до города, дойти домой", epicTask.getId()));
        epicTask.addNewSubTask(subTask1);
        epicTask.addNewSubTask(subTask2);
        epicTask.addNewSubTask(subTask3);
        epicTask.addNewSubTask(subTask4);
        System.out.println(epicTask.getSubTaskList());
        System.out.println(epicTask);

        Task aTask = taskManager.getTask(new Task("Помыться", "прост помыться"));
        Task secondTask = taskManager.getTask(new Task("побриться", "прост побриться бы"));
        System.out.println(aTask);
        System.out.println(secondTask);





    //создал эпик и подзадачу, изменил статус подзадачи, сохранил эпик задачу в менеджере, но не сохранил статус подзадачи
    EpicTask epicTask2 = taskManager.getEpicTask(new EpicTask("Прост", "123"));
    SubTask subTask = taskManager.getSubTask(new SubTask("прост_сабтаск", "просто-просто", epicTask2.getId()));
        epicTask2.addNewSubTask(subTask);

                subTask.setStatus(Status.IN_PROGRESS);
                taskManager.updateTask(epicTask2);
                //System.out.println(epicTask2);



                subTask.setStatus(Status.DONE);
                taskManager.updateTask(subTask);
                System.out.println(subTask);
                taskManager.updateTask(epicTask2);
                System.out.println(epicTask2);*/