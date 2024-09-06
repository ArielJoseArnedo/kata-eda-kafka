package co.com.pichincha.openbanking.infrastructure.publishers.kafka;


import co.com.pichincha.openbanking.infrastructure.events.Event;
import co.com.pichincha.openbanking.infrastructure.publishers.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaPublisher implements Publisher {

    private final String topic;
    private final ReactiveKafkaProducerTemplate<String, Event<?>> producer;

    @Autowired
    public KafkaPublisher(@Value("${spring.kafka.producer.topic}") String topic, ReactiveKafkaProducerTemplate<String, Event<?>> producer) {
        this.topic = topic;
        this.producer = producer;
    }

    @Override
    public void send(Event<?> event) {
        log.info("Se envia el evento: {}", event.getName());
        producer.send(topic, event)
          .doOnSuccess(v -> log.info("El evento: {} fue enviado con exito", event.getName()))
          .doOnError(error -> log.warn("Hubo un error enviando en evento :{}" , event.getName(), error))
          .subscribe();
    }
}
