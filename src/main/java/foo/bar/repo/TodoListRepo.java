package foo.bar.repo;

import foo.bar.domain.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for TodoLists.
 */
@Repository
public interface TodoListRepo extends JpaRepository<TodoList, String> {
}
