package foo.bar.handlers;

import foo.bar.events.*;
import foo.bar.models.TodoItemEntry;
import foo.bar.models.TodoListEntry;
import foo.bar.repo.TodoItemEntryRepo;
import foo.bar.repo.TodoListEntryRepo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by joel on 11/17/2016.
 */
@Component
public class TodoListEventHandler {
    private final TodoListEntryRepo todoListEntryRepo;
    private final TodoItemEntryRepo todoItemEntryRepo;

    public TodoListEventHandler(TodoListEntryRepo todoListEntryRepo,
            TodoItemEntryRepo todoItemEntryRepo) {
        this.todoListEntryRepo = todoListEntryRepo;
        this.todoItemEntryRepo = todoItemEntryRepo;
    }

    @EventHandler
    @Transactional
    public void on(TodoListCreatedEvent event) {
        todoListEntryRepo.save(
                new TodoListEntry(
                        event.getTodoListId(),
                        event.getName()));
    }

    @EventHandler
    @Transactional
    public void on(TodoListRenamedEvent event) {
        TodoListEntry todoListEntry = todoListEntryRepo.getOne(event.getTodoListId());
        todoListEntry.setName(event.getName());
        todoListEntryRepo.save(todoListEntry);
    }

    @EventHandler
    @Transactional
    public void on(TodoListArchivedEvent event) {
        TodoListEntry todoListEntry = todoListEntryRepo.getOne(event.getTodoListId());
        todoListEntry.setArchived(true);
        todoListEntryRepo.save(todoListEntry);
    }

    @EventHandler
    @Transactional
    public void on(TodoItemAddedToListEvent event) {
        TodoListEntry todoListEntry = todoListEntryRepo.getOne(event.getTodoListId());
        todoListEntry.setTodoCount(todoListEntry.getTodoCount()+1);

        TodoItemEntry todoItemEntry = new TodoItemEntry(
                event.getTodoItemId(), event.getTodoListId(), event.getTodoItemName());

        todoItemEntryRepo.save(todoItemEntry);
        todoListEntryRepo.save(todoListEntry);
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
    public void on(TodoItemArchivedEvent event) {
        TodoItemEntry todoItemEntry = todoItemEntryRepo.findOne(event.getTodoItemId());
        todoItemEntry.setArchived(true);
        todoItemEntryRepo.save(todoItemEntry);
    }
}
