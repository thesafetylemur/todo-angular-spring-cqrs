package foo.bar.events;

public class TodoListRenamedEvent {

    private final String todoListId;
    private final String name;

    public TodoListRenamedEvent(String todoListId, String name) {
        this.todoListId = todoListId;
        this.name = name;
    }

    public String getTodoListId() {
        return todoListId;
    }

    public String getName() {
        return name;
    }
}
