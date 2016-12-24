package foo.bar.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Map;

/**
 * Created by joelszymczak on 12/24/16.
 */
public class RenameTodoItemCommand {
    @TargetAggregateIdentifier
    private final String todoListId;
    private final String todoItemId;
    private final String todoItemName;

    public RenameTodoItemCommand(String todoListId, String todoItemId, String todoItemName) {
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
