package foo.bar.web;

import foo.bar.commands.RenameTodoItemCommand;
import foo.bar.commands.*;
import foo.bar.models.TodoItemEntry;
import foo.bar.models.TodoListEntry;
import foo.bar.repo.TodoItemEntryRepo;
import foo.bar.repo.TodoListEntryRepo;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

//import foo.bar.repo.TodoItemRepo;
//import foo.bar.repo.TodoListRepo;

/**
 * Created by joel on 11/17/2016.
 */
@RequestMapping("/todoLists")
@RestController
public class TodoListAPI {

    private final CommandGateway commandGateway;
    private final TodoListEntryRepo todoListEntryRepo;
    private final TodoItemEntryRepo todoItemEntryRepo;

    public TodoListAPI(
            CommandGateway commandGateway,
            TodoListEntryRepo todoListEntryRepo,
            TodoItemEntryRepo todoItemEntryRepo) {
        this.commandGateway = commandGateway;
        this.todoListEntryRepo = todoListEntryRepo;
        this.todoItemEntryRepo = todoItemEntryRepo;
    }

    @GetMapping
    public List<TodoListEntry> findAll() {
        return todoListEntryRepo.findAll();
    }

    @GetMapping("/{todoListId}")
    public TodoListEntry find(@PathVariable String todoListId) {
        return todoListEntryRepo.findOne(todoListId);
    }

    @PostMapping
    public CompletableFuture<String> createTodoList(@RequestBody Map<String, String> request) {
        final String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateTodoListCommand(id, request.get("name")));
    }

    @PutMapping("/{todoListId}")
    public CompletableFuture<String> renameTodoList(
            @PathVariable String todoListId,
            @RequestParam String name) {
        return commandGateway.send(new RenameTodoListCommand(todoListId, name));
    }

    @DeleteMapping("/{todoListId}")
    public CompletableFuture<String> archiveTodoList(@PathVariable String todoListId) {
        return commandGateway.send(new ArchiveTodoListCommand(todoListId));
    }

    @GetMapping("/{todoListId}/items")
    public List<TodoItemEntry> getTodoListItems(@PathVariable String todoListId) {
        return todoItemEntryRepo.findByTodoListId(todoListId);
    }

    @PostMapping("/{todoListId}/items")
    public CompletableFuture<String> createTodoItem(
            @RequestBody Map<String, String> request,
            @PathVariable String todoListId) {
        final String id = UUID.randomUUID().toString();
        return commandGateway.send(new AddTodoItemToListCommand(todoListId, id, request.get("name")));
    }

    @PutMapping(value = "/{todoListId}/items/{todoItemId}", params = "name")
    public CompletableFuture<String> renameTodoItem(
            @PathVariable String todoListId,
            @PathVariable String todoItemId,
            @RequestParam String name) {
        return commandGateway.send(new RenameTodoItemCommand(todoListId, todoItemId, name));
    }

    @PutMapping("/{todoListId}/items/{todoItemId}")
    public CompletableFuture<String> completeTodoItem(
            @PathVariable String todoListId,
            @PathVariable String todoItemId) {
        return commandGateway.send(new CompleteTodoItemCommand(todoListId, todoItemId));
    }

    @DeleteMapping("/{todoListId}/items/{todoItemId}")
    public CompletableFuture<String> archiveTodoItem(
            @PathVariable String todoListId,
            @PathVariable String todoItemId) {
        return commandGateway.send(new ArchiveTodoItemCommand(todoListId, todoItemId));
    }
}
