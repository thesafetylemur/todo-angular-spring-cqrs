package foo.bar.commands;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class RenameTodoListCommand extends Command{

    @TargetAggregateIdentifier
    private final String todoListId;
    private final String name;

    public RenameTodoListCommand(String todoListId, String name) {
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
