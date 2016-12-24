package foo.bar.domain;

/**
 * A TodoList item.
 */
public class TodoItem {
    private String id;
    private String todoListId;
    private String name;
    private boolean completed;
    private boolean archived;

    public TodoItem() { }

    public TodoItem(String id, String name) {
        this.id = id;
        this.name = name;
        this.completed = false;
    }

    public String getId() {
        return id;
    }

    public String getTodoListId() {
        return todoListId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isArchived() {
        return this.archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
