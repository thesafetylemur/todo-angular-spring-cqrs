package foo.bar.config;

import foo.bar.domain.TodoList;
import org.axonframework.common.jdbc.ConnectionProvider;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.jdbc.UnitOfWorkAwareConnectionProviderWrapper;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcSQLErrorCodesResolver;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.upcasting.event.EventUpcaster;
import org.axonframework.serialization.upcasting.event.EventUpcasterChain;
import org.axonframework.spring.jdbc.SpringDataSourceConnectionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by joel on 12/21/2016.
 */
@Configuration
public class AxonConfig {

    // FIXME: Needs to be more configurable; Use profiles to run via mysql/h2/whatever..?
    @Bean
    public EventSourcingRepository<TodoList> taskRepository(DataSource dataSource) {
        Serializer serializer = new JacksonSerializer();
        EventUpcaster eventUpcaster = new EventUpcasterChain();
        PersistenceExceptionResolver persistenceExceptionResolver = new JdbcSQLErrorCodesResolver();
        ConnectionProvider connectionProvider = new UnitOfWorkAwareConnectionProviderWrapper(
                new SpringDataSourceConnectionProvider(dataSource));

        EventStorageEngine eventStorageEngine = new JdbcEventStorageEngine(serializer, eventUpcaster,
                persistenceExceptionResolver, connectionProvider);
        EventStore es =  new EmbeddedEventStore(eventStorageEngine);

        EventSourcingRepository<TodoList> repository = new EventSourcingRepository<>(TodoList.class, es);
        return repository;
    }
}
