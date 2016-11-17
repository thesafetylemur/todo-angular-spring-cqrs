package foo.bar.web;

import foo.bar.commands.AddTodoItemToListCommand;
import foo.bar.commands.CreateTodoListCommand;
import foo.bar.domain.TodoList;
import foo.bar.repo.TodoItemRepo;
import foo.bar.repo.TodoListRepo;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by chq-joels on 11/17/2016.
 */
@RequestMapping("/todoLists")
@RestController
public class TodoListAPI {

    private final CommandGateway commandGateway;
    private final TodoListRepo todoListRepo;
    private final TodoItemRepo todoItemRepo;

    public TodoListAPI(
            CommandGateway commandGateway,
            TodoListRepo todoListRepo,
            TodoItemRepo todoItemRepo) {
        this.commandGateway = commandGateway;
        this.todoListRepo = todoListRepo;
        this.todoItemRepo = todoItemRepo;
    }

    @GetMapping
    public List<TodoList> findAll() {
        return todoListRepo.findAll();
    }

    @GetMapping("/{todoListId}")
    public TodoList find(@PathVariable String todoListId) {
        return todoListRepo.findOne(todoListId);
    }

    @PostMapping
    public CompletableFuture<String> createTodoList(@RequestBody Map<String, String> request) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateTodoListCommand(id, request.get("name")));
    }

// TODO: Implement this
//    @DeleteMapping
//    public CompletableFuture<String> deleteTodoList(@PathVariable String todoListId) {
//        return commandGateway.send(new DeleteTodoListCommand(todoListId));
//    }

    @PostMapping("/{todoListId}/items/")
    public CompletableFuture<String> createTodoItem(
            @RequestBody Map<String, String> request,
            @PathVariable String todoListId) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new AddTodoItemToListCommand(id, todoListId, request.get("name")));
    }
}
