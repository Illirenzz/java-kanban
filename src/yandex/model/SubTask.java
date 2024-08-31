package yandex.model;

public class SubTask extends Task {
    private final int epicID;

    public SubTask(String name, String description, int epicID) {
        super(name, description);
        this.epicID = epicID;
    }
    public int getEpicID() {
        return epicID;
    }

}
