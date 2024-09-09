package yandex.app.service;

import yandex.app.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> viewHistory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (viewHistory.size() == 10) {
            viewHistory.remove(0);
        }
        viewHistory.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return viewHistory;
    }
}
