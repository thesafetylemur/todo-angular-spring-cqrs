package foo.bar.repo;


import foo.bar.models.TodoItemEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for TodoItems.
 */
@Repository
public interface TodoItemEntryRepo extends JpaRepository<TodoItemEntry, String> {
    List<TodoItemEntry> findByTodoListId(String todoListId);
}