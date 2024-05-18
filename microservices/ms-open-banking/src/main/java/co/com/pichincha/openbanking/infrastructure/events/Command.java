package co.com.pichincha.openbanking.infrastructure.events;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface Command <R, T, E extends Event>{

    default Mono<Tuple2<R, E>> execute(T request) {
        return Mono.empty();
    }
}
