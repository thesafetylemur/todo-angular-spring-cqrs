package foo.bar.models;

import javax.persistence.*;

/**
 * Created by joel on 12/22/2016.
 */
@Entity
public class TodoItemEntry {
    @Id
    private String todoItemId;
    private String todoListId;
    private String name;
    private boolean completed;;
    private boolean archived;

    public TodoItemEntry() { }

    public TodoItemEntry(String todoItemId, String todoListId, String name) {
        this.todoItemId = todoItemId;
        this.todoListId = todoListId;
        this.name = name;
    }

    public String getTodoItemId() {
        return todoItemId;
    }

    public void setTodoItemId(String todoItemId) {
        this.todoItemId = todoItemId;
    }

    public String getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(String todoListId) {
        this.todoListId = todoListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
