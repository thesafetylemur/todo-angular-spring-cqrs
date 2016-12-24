package foo.bar.events;

/**
 * Created by joelszymczak on 12/24/16.
 */
public class TodoItemArchivedEvent {
    private final String todoListId;
    private final String todoItemId;

    public TodoItemArchivedEvent(String todoListId, String todoItemId) {
        this.todoListId = todoListId;
        this.todoItemId = todoItemId;
    }

    public String getTodoListId() {
        return todoListId;
    }

    public String getTodoItemId() {
        return todoItemId;
    }
}
