package foo.bar.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

/**
 * Adds a TodoItem to a TodoList.
 */
public class AddTodoItemToListCommand extends Command {
    @TargetAggregateIdentifier
    @NotNull
    private final String id;
    private final String todoListId;
    private final String todoName;

    public AddTodoItemToListCommand(
            String id,
            String todoListId,
            String todoName) {
        this.id = id;
        this.todoListId = todoListId;
        this.todoName = todoName;
    }

    public String getId() {
        return id;
    }

    public String getTodoListId() {
        return todoListId;
    }

    public String getTodoName() {
        return todoName;
    }
}
