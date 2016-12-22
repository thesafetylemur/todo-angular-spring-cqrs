package foo.bar.domain;

/**
 * A TodoList item.
 */
public class TodoItem {
    private String id;
    private String name;
    private boolean completed;

    public TodoItem() { }

    public TodoItem(String id, String name) {
        this.id = id;
        this.name = name;
        this.completed = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.completed;
    }
}
