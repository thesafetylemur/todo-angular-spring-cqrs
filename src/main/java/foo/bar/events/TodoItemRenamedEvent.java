package foo.bar.events;

/**
 * Created by joel on 12/22/2016.
 */
public class TodoItemRenamedEvent {
    private final String todoListId;
    private final String todoItemId;
    private final String todoItemName;

    public TodoItemRenamedEvent(String todoListId, String todoItemId, String todoItemName) {
        this.todoListId = todoListId;
        this.todoItemId = todoItemId;
        this.todoItemName = todoItemName;
    }

    public String getTodoListId() {
        return todoListId;
    }

    public String getTodoItemId() {
        return todoItemId;
    }

    public String getTodoItemName() {
        return todoItemName;
    }
}
