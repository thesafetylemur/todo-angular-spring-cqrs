package foo.bar.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A TodoList item.
 */
@Entity
public class TodoItem {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
