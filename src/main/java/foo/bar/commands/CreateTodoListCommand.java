package foo.bar.commands;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Command to create a TodoList.
 */
public class CreateTodoListCommand extends Command {

    @TargetAggregateIdentifier
    private final String todoListId;
    private final String description;

    public CreateTodoListCommand(String todoListId, String name) {
        this.todoListId = todoListId;
        this.description = name;
    }

    public String getTodoListId() {
        return todoListId;
    }

    public String getDescription() {
        return description;
    }
}
