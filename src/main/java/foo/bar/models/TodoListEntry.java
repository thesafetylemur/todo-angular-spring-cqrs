package foo.bar.models;

import javax.persistence.*;

/**
 * Created by joel on 12/22/2016.
 */
@Entity
public class TodoListEntry {
    @Id
    private String todoListId;
    private String name;
    private boolean archived;
    private int completedCount;
    private int todoCount;

    public TodoListEntry() {
    }

    public TodoListEntry(String todoListId, String name) {
        this();
        this.todoListId = todoListId;
        this.name = name;
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

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    public int getTodoCount() {
        return todoCount;
    }

    public void setTodoCount(int todoCount) {
        this.todoCount = todoCount;
    }
}
