package foo.bar.handlers;

import foo.bar.events.*;
import foo.bar.models.TodoListEntry;
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

    public TodoListEventHandler(TodoListEntryRepo todoListEntryRepo) {
        this.todoListEntryRepo = todoListEntryRepo;
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
        todoListEntryRepo.save(todoListEntry);
    }
}
