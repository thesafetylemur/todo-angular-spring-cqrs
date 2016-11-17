package foo.bar.repo;

import foo.bar.domain.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for TodoItems.
 */
@Repository
public interface TodoItemRepo extends JpaRepository<TodoItem, String> {
}