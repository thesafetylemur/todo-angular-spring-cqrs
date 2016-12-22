package foo.bar.repo;

import foo.bar.models.TodoListEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for TodoLists.
 */
@Repository
public interface TodoListEntryRepo extends JpaRepository<TodoListEntry, String> {
}
