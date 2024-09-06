package co.com.pichincha.users.infrastructure.events;

import co.com.pichincha.users.infrastructure.publishers.Publisher;
import co.com.pichincha.users.infrastructure.publishers.kafka.KafkaPublisher;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public abstract class CommandProcessor {

    private final Publisher publisher;

    public CommandProcessor(Publisher publisher) {
        this.publisher = publisher;
    }

    protected  <R, T, E extends Event> Mono<ResponseEntity<R>>executeCommand(Command<R, T, E> command, T request) {
        return command.execute(request)
          .map(tuple2 -> tuple2.mapT1(ResponseEntity::ok))
          .map(tuple2 -> {
              CompletableFuture.runAsync(() -> publisher.send(tuple2.getT2()));
              return tuple2.getT1();
          });
    }
}
