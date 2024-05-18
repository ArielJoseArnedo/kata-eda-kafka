package co.com.pichincha.users.infrastructure.events;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public interface CommandProcessor {

    default <R, T, E extends Event> Mono<ResponseEntity<R>>executeCommand(Command<R, T, E> command, T request) {
        return command.execute(request)
          .map(tuple2 -> tuple2.mapT1(ResponseEntity::ok))
          .map(tuple2 -> {
              CompletableFuture.runAsync(() -> new Publisher().sendEvent(tuple2.getT2()));
              return tuple2.getT1();
          });
    }
}
