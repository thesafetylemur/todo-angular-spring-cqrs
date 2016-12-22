package foo.bar.web;

import foo.bar.commands.AddTodoItemToListCommand;
import foo.bar.commands.CreateTodoListCommand;
import foo.bar.commands.RenameTodoListCommand;
//import foo.bar.repo.TodoItemRepo;
//import foo.bar.repo.TodoListRepo;
import foo.bar.models.TodoListEntry;
import foo.bar.repo.TodoListEntryRepo;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by joel on 11/17/2016.
 */
@RequestMapping("/todoLists")
@RestController
public class TodoListAPI {

    private final CommandGateway commandGateway;
    private final TodoListEntryRepo todoListEntryRepo;
//    private final TodoItemRepo todoItemRepo;

    public TodoListAPI(
            CommandGateway commandGateway,
            TodoListEntryRepo todoListEntryRepo
    ) {
        this.commandGateway = commandGateway;
        this.todoListEntryRepo = todoListEntryRepo;
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
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateTodoListCommand(id, request.get("name")));
    }

//    @PostMapping("/test1")
//    public void useDemoStuff(@RequestBody Map<String, String> request) {
//        // let's send some Commands on the CommandBus.
//        final String listId = UUID.randomUUID().toString();
//        commandGateway.send(new CreateTodoListCommand(listId, "Something"));
//        commandGateway.send(new RenameTodoListCommand(listId, "foo"));
//        String itemId = UUID.randomUUID().toString();
//        commandGateway.send(new AddTodoItemToListCommand(listId, itemId, "My Todo Task"));
//        commandGateway.send(new RenameTodoListCommand(listId, "bar"));
//        itemId = UUID.randomUUID().toString();
//        commandGateway.send(new AddTodoItemToListCommand(listId, itemId, "My second Task"));
//    }

// TODO: Implement this
//    @DeleteMapping
//    public CompletableFuture<String> deleteTodoList(@PathVariable String todoListId) {
//        return commandGateway.send(new DeleteTodoListCommand(todoListId));
//    }

    @PostMapping("/{todoListId}/items")
    public CompletableFuture<String> createTodoItem(
            @RequestBody Map<String, String> request,
            @PathVariable String todoListId) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new AddTodoItemToListCommand(todoListId, id, request.get("name")));
    }
}
