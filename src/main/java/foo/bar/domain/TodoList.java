package foo.bar.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * A list of TodoItems.
 */
@Entity
public class TodoList {

    @Id
    private String id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    private List<TodoItem> items;

    public TodoList(
            String id,
            String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<TodoItem> getItems() {
        return items;
    }

    public void setItems(List<TodoItem> items) {
        this.items = items;
    }
}
