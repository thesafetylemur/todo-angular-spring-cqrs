package foo.bar.models;

import javax.persistence.*;

/**
 * Created by joel on 12/22/2016.
 */
@Entity
public class TodoItemEntry {
    @Id
    private String todoItemId;
    @ManyToOne
    private TodoListEntry todoListEntry;
    private String name;
    private boolean completed;

    public TodoItemEntry() { }

    public TodoItemEntry(String todoItemId, TodoListEntry todoListEntry, String name, boolean completed) {
        this.todoItemId = todoItemId;
        this.todoListEntry = todoListEntry;
        this.name = name;
        this.completed = completed;
    }

    public String getTodoItemId() {
        return todoItemId;
    }

    public void setTodoItemId(String todoItemId) {
        this.todoItemId = todoItemId;
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
}
