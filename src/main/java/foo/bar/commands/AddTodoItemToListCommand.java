package foo.bar.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Adds a TodoItem to a TodoList.
 */
public class AddTodoItemToListCommand extends Command {
    @TargetAggregateIdentifier
    private final String todoListId;
    private final String todoItemId;
    private final String todoItemName;

    public AddTodoItemToListCommand(String todoListId, String todoItemId, String todoItemName) {
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
