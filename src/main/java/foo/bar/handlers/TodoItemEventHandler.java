package foo.bar.handlers;

import foo.bar.events.*;
import foo.bar.models.TodoItemEntry;
import foo.bar.repo.TodoItemEntryRepo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by joel on 11/17/2016.
 */
@Component
public class TodoItemEventHandler {
    private final TodoItemEntryRepo todoItemEntryRepo;

    public TodoItemEventHandler(TodoItemEntryRepo todoItemEntryRepo) {
        this.todoItemEntryRepo = todoItemEntryRepo;
    }

    @EventHandler
    @Transactional
    public void on(TodoItemRenamedEvent event) {
        TodoItemEntry todoItemEntry = todoItemEntryRepo.findOne(event.getTodoItemId());
        todoItemEntry.setName(event.getTodoItemName());
        todoItemEntryRepo.save(todoItemEntry);
    }

    @EventHandler
    @Transactional
    public void on(TodoItemCompletedEvent event) {
        TodoItemEntry todoItemEntry = todoItemEntryRepo.findOne(event.getTodoItemId());
        todoItemEntry.setCompleted(true);
        todoItemEntryRepo.save(todoItemEntry);
    }

    @EventHandler
    @Transactional
    public void on(TodoItemUncompletedEvent event) {
        TodoItemEntry todoItemEntry = todoItemEntryRepo.findOne(event.getTodoItemId());
        todoItemEntry.setCompleted(false);
        todoItemEntryRepo.save(todoItemEntry);
    }

    @EventHandler
    @Transactional
    public void on(TodoItemArchivedEvent event) {
        TodoItemEntry todoItemEntry = todoItemEntryRepo.findOne(event.getTodoItemId());
        todoItemEntry.setArchived(true);
        todoItemEntryRepo.save(todoItemEntry);
    }

    @EventHandler
    @Transactional
    public void on(TodoItemAddedToListEvent event) {
        TodoItemEntry todoItemEntry = new TodoItemEntry(
                event.getTodoItemId(), event.getTodoListId(), event.getTodoItemName());
        todoItemEntryRepo.save(todoItemEntry);
    }
}
