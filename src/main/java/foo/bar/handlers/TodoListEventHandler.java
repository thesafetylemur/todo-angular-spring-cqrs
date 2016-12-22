package foo.bar.handlers;

import foo.bar.events.TodoItemAddedToListEvent;
import foo.bar.events.TodoListCreatedEvent;
import foo.bar.events.TodoListRenamedEvent;
import foo.bar.models.TodoItemEntry;
import foo.bar.models.TodoListEntry;
import foo.bar.repo.TodoListEntryRepo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by joel on 11/17/2016.
 */
@Component
public class TodoListEventHandler {
    private final TodoListEntryRepo todoListEntryRepo;

    public TodoListEventHandler(TodoListEntryRepo todoListEntryRepo) {
        this.todoListEntryRepo = todoListEntryRepo;
    }

    @EventHandler
    public void on(TodoListCreatedEvent todoListCreatedEvent) {
        todoListEntryRepo.save(
                new TodoListEntry(
                        todoListCreatedEvent.getTodoListId(),
                        todoListCreatedEvent.getName()));
    }

    @EventHandler
    public void on(TodoListRenamedEvent todoListRenamedEvent) {
        TodoListEntry todoListEntry = todoListEntryRepo.getOne(todoListRenamedEvent.getTodoListId());
        todoListEntry.setName(todoListRenamedEvent.getName());
        todoListEntryRepo.save(todoListEntry);
    }

    @EventHandler
    public void on(TodoItemAddedToListEvent todoItemAddedToListEvent) {
        TodoListEntry todoListEntry = todoListEntryRepo.getOne(todoItemAddedToListEvent.getTodoListId());
        todoListEntry.getTodoItems().add(
                new TodoItemEntry(
                        todoItemAddedToListEvent.getTodoItemId(),
                        todoListEntry,
                        todoItemAddedToListEvent.getTodoItemName(),
                        false));
        todoListEntryRepo.save(todoListEntry);
    }
}
