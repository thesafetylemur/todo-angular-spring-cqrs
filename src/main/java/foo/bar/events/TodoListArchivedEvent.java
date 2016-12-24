package foo.bar.events;

/**
 * Created by joelszymczak on 12/24/16.
 */
public class TodoListArchivedEvent {
    private final String todoListId;

    public TodoListArchivedEvent(String todoListId) {
        this.todoListId = todoListId;
    }

    public String getTodoListId() {
        return todoListId;
    }

}
