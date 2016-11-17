package foo.bar.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

/**
 * Command to create a TodoList.
 */
public class CreateTodoListCommand extends Command {
    @TargetAggregateIdentifier
    @NotNull
    private final String id;
    private final String name;

    public CreateTodoListCommand(
            String id,
            String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
