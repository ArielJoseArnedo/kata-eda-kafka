package co.com.pichincha.openbanking.infrastructure.events;

import reactor.core.publisher.Mono;

public interface Listener {

    String name();

    default Mono<Event> process(Event event) {
        return Mono.empty();
    }
}
