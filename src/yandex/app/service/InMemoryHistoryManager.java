package yandex.app.service;

import yandex.app.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int HISTORY_MAX_LENGTH = 10;

    /**
     * по поводу использования связанного списка. При использовании списка длинной в 10 разницы в скорости не будет.
     * про разницу в реализации списков я, может, и не идеально, но знаю.
     * LinkedList было бы уместно использовать при более длинной хранимой истории.
     * т.к. метод возвращает List, при необходимости, в любой момент можно поменять переменную на LinkedList.
     * указывать в конструкторе требуемый размер (10 ячеек) необходимости нет, т.к. дефолтное значение ArrayList = 10.
     */
    private final List<Task> viewHistory = new ArrayList<>();


    @Override
    public void add(Task task) {
        if (viewHistory.size() == HISTORY_MAX_LENGTH) {
            viewHistory.remove(0);
        }
        viewHistory.add(task);
    }


    @Override
    public List<Task> getHistory() {
        return List.copyOf(viewHistory);
    }

}
