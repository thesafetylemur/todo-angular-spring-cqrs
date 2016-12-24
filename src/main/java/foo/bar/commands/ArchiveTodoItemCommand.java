package foo.bar.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by joelszymczak on 12/24/16.
 */
public class ArchiveTodoItemCommand {
    @TargetAggregateIdentifier
    private final String todoListId;
    private final String todoItemId;

    public ArchiveTodoItemCommand(String todoListId, String todoItemId) {
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
