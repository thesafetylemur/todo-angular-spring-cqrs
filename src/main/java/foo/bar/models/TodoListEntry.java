package foo.bar.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 12/22/2016.
 */
@Entity
public class TodoListEntry {
    @Id
    private String todoListId;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "todoListEntry", orphanRemoval = true)
    private List<TodoItemEntry> todoItems;

    public TodoListEntry() {
        this.todoItems = new ArrayList<>();
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

    public List<TodoItemEntry> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItemEntry> todoItems) {
        this.todoItems = todoItems;
    }
}
