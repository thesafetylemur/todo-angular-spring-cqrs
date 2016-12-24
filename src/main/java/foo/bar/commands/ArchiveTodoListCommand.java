package foo.bar.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by joelszymczak on 12/24/16.
 */
public class ArchiveTodoListCommand extends Command {

    @TargetAggregateIdentifier
    private final String todoListId;

    public ArchiveTodoListCommand(String todoListId) {
        this.todoListId = todoListId;
    }

    public String getTodoListId() {
        return todoListId;
    }

}
