package foo.bar.handlers;

import foo.bar.commands.CreateTodoListCommand;
import foo.bar.domain.TodoList;
import foo.bar.repo.TodoListRepo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by chq-joels on 11/17/2016.
 */
@Component
public class TodoListEventHandler {
    private final TodoListRepo todoListRepo;

    public TodoListEventHandler(TodoListRepo todoListRepo) {
        this.todoListRepo = todoListRepo;
    }

    @EventHandler
    public void on(CreateTodoListCommand createTodoListCommand) {
        todoListRepo.save(new TodoList(createTodoListCommand.getId(), createTodoListCommand.getName()));
    }
}
