package co.com.pichincha.users.infrastructure.publishers.kafka;

import co.com.pichincha.users.infrastructure.events.Event;
import co.com.pichincha.users.infrastructure.publishers.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class KafkaPublisher implements Publisher {

    private final String topic;
    private final ReactiveKafkaProducerTemplate<String, String> producer;

    @Autowired
    public KafkaPublisher(@Value("${spring.kafka.producer.topic}") String topic, ReactiveKafkaProducerTemplate<String, String> producer) {
        this.topic = topic;
        this.producer = producer;
    }

    @Override
    public void send(Event<?> event) {
        log.info("Se envia el evento: {}", event.getName());

        Mono.fromCallable(() -> toString(event))
          .flatMap(eventString -> producer.send(topic, eventString))
          .doOnSuccess(v -> log.info("El evento: {} fue enviado con exito", event.getName()))
          .doOnError(error -> log.warn("Hubo un error enviando en evento :{}", event.getName(), error))
          .subscribe();
    }
}
