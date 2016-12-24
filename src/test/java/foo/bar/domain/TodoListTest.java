package foo.bar.domain;

import foo.bar.commands.CreateTodoListCommand;
import foo.bar.events.TodoListCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the TodoList aggregate commands/events.
 */
public class TodoListTest {

    private FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture(TodoList.class);
    }

    @Test
    public void test_something() {
        fixture
                // Given any prior events...
                .given()
                // ... when a CreateTodoListCommand is submitted...
                .when(new CreateTodoListCommand("12345", "My Todo List"))
                // ... a TodoListCreatedEvent should be emitted...
                .expectEvents(new TodoListCreatedEvent("12345", "My Todo List"))
        ;
    }

    // TODO: Implement other test scenarios!

}
