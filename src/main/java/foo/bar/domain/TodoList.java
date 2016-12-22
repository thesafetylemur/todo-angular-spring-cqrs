package foo.bar.domain;

import foo.bar.commands.AddTodoItemToListCommand;
import foo.bar.commands.CreateTodoListCommand;
import foo.bar.commands.RenameTodoListCommand;
import foo.bar.events.TodoItemAddedToListEvent;
import foo.bar.events.TodoListCreatedEvent;
import foo.bar.events.TodoListRenamedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class TodoList {
    @AggregateIdentifier
    private String id;
    private String name;
    private List<TodoItem> todoItems;

    public TodoList() {
        this.todoItems = new ArrayList<>();
    }

    @CommandHandler
    public TodoList(CreateTodoListCommand command) {
        apply(new TodoListCreatedEvent(command.getTodoListId(), command.getDescription()));
    }

    @EventSourcingHandler
    public void on(TodoListCreatedEvent event) {
        this.id = event.getTodoListId();
        this.name = event.getName();
    }

    @CommandHandler
    public void renameTodoList(RenameTodoListCommand command) {
        apply(new TodoListRenamedEvent(command.getTodoListId(), command.getName()));
    }

    @EventSourcingHandler
    public void on(TodoListRenamedEvent event) {
        this.name = event.getName();
    }

    @CommandHandler
    public void addTodoItemToList(AddTodoItemToListCommand command) {
        apply(new TodoItemAddedToListEvent(
                command.getTodoListId(),
                command.getTodoItemId(),
                command.getTodoItemName()));
    }

    @EventSourcingHandler
    public void on(TodoItemAddedToListEvent event) {
        this.todoItems.add(new TodoItem(event.getTodoItemId(), event.getTodoItemName()));
    }
}