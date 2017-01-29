package foo.bar.web;

import foo.bar.commands.RenameTodoItemCommand;
import foo.bar.commands.*;
import foo.bar.models.TodoItemEntry;
import foo.bar.models.TodoListEntry;
import foo.bar.repo.TodoItemEntryRepo;
import foo.bar.repo.TodoListEntryRepo;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public ResponseEntity createTodoList(@RequestBody Map<String, String> request) {
        final String id = UUID.randomUUID().toString();
        commandGateway.send(new CreateTodoListCommand(id, request.get("name")));
        // TODO: It'd be nice if we didn't have to query for this after we just created it..
        // There's gotta be a more efficient way of doing this!
        return ResponseEntity.ok(todoListEntryRepo.findOne(id));
    }

    @PutMapping("/{todoListId}")
    public void renameTodoList(
            @PathVariable String todoListId,
            @RequestParam String name) {
        commandGateway.send(new RenameTodoListCommand(todoListId, name));
    }

    @DeleteMapping("/{todoListId}")
    public void archiveTodoList(@PathVariable String todoListId) {
        commandGateway.send(new ArchiveTodoListCommand(todoListId));
    }

    @GetMapping("/{todoListId}/items")
    public List<TodoItemEntry> getTodoListItems(@PathVariable String todoListId) {
        return todoItemEntryRepo.findByTodoListId(todoListId);
    }

    @PostMapping("/{todoListId}/items")
    public ResponseEntity createTodoItem(
            @RequestBody Map<String, String> request,
            @PathVariable String todoListId) {
        final String id = UUID.randomUUID().toString();
        commandGateway.send(new AddTodoItemToListCommand(todoListId, id, request.get("name")));
        // TODO: It'd be nice if we didn't have to query for this after we just created it..
        // There's gotta be a more efficient way of doing this!
        return ResponseEntity.ok(todoItemEntryRepo.findOne(id));
    }

    @PutMapping(value = "/{todoListId}/items/{todoItemId}", params = "name")
    public void renameTodoItem(
            @PathVariable String todoListId,
            @PathVariable String todoItemId,
            @RequestParam String name) {
        commandGateway.send(new RenameTodoItemCommand(todoListId, todoItemId, name));
    }

    @PutMapping("/{todoListId}/items/{todoItemId}")
    public void completeTodoItem(
            @PathVariable String todoListId,
            @PathVariable String todoItemId) {
        commandGateway.send(new CompleteTodoItemCommand(todoListId, todoItemId));
    }

    @DeleteMapping("/{todoListId}/items/{todoItemId}")
    public void archiveTodoItem(
            @PathVariable String todoListId,
            @PathVariable String todoItemId) {
        commandGateway.send(new ArchiveTodoItemCommand(todoListId, todoItemId));
    }
}
