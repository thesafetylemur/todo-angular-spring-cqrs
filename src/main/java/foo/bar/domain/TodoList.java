package foo.bar.domain;

import foo.bar.commands.*;
import foo.bar.events.*;
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
    private boolean archived;
    private List<TodoItem> todoItems;

    public TodoList() {
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
    public void archiveTodoList(ArchiveTodoListCommand command) {
        apply(new TodoListArchivedEvent(command.getTodoListId()));
    }

    @EventSourcingHandler
    public void on(TodoListArchivedEvent event) {
        this.archived = true;
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
        if (this.todoItems == null) {
            this.todoItems = new ArrayList<>();
        }
        this.todoItems.add(new TodoItem(event.getTodoItemId(), event.getTodoItemName()));
    }

    @CommandHandler
    public void renameTodoItem(RenameTodoItemCommand command) {
        apply(new TodoItemRenamedEvent(
                command.getTodoListId(),
                command.getTodoItemId(),
                command.getTodoItemName()));
    }

    @EventSourcingHandler
    public void on(TodoItemRenamedEvent event) {
        // TODO: It'd be more efficient to manage the TodoItems in a map so we don't have to loop here...
        this.todoItems.forEach(item -> {
            if (item.getId().equals(event.getTodoItemId())) {
                item.setName(event.getTodoItemName());
            }
        });
    }

    @CommandHandler
    public void completeTodoItem(CompleteTodoItemCommand command) {
        apply(new TodoItemCompletedEvent(
                command.getTodoListId(),
                command.getTodoItemId()));
    }

    @EventSourcingHandler
    public void on(TodoItemCompletedEvent event) {
        // TODO: It'd be more efficient to manage the TodoItems in a map so we don't have to loop here...
        this.todoItems.forEach(item -> {
            if (item.getId().equals(event.getTodoItemId())) {
                item.setCompleted(true);
            }
        });
    }

    @CommandHandler
    public void uncompleteTodoItem(UncompleteTodoItemCommand command) {
        apply(new TodoItemUncompletedEvent(
                command.getTodoListId(),
                command.getTodoItemId()));
    }

    @EventSourcingHandler
    public void on(TodoItemUncompletedEvent event) {
        // TODO: It'd be more efficient to manage the TodoItems in a map so we don't have to loop here...
        this.todoItems.forEach(item -> {
            if (item.getId().equals(event.getTodoItemId())) {
                item.setCompleted(false);
            }
        });
    }

    @CommandHandler
    public void archiveTodoItem(ArchiveTodoItemCommand command) {
        apply(new TodoItemArchivedEvent(
                command.getTodoListId(),
                command.getTodoItemId()));
    }

    @EventSourcingHandler
    public void on(TodoItemArchivedEvent event) {
        // TODO: It'd be more efficient to manage the TodoItems in a map so we don't have to loop here...
        this.todoItems.forEach(item -> {
            if (item.getId().equals(event.getTodoItemId())) {
                item.setArchived(true);
            }
        });
    }
}