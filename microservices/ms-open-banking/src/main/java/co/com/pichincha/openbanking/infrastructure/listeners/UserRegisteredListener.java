package co.com.pichincha.openbanking.infrastructure.listeners;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRegisteredListener {

    @Listener(id = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.consumer.topic}")
    public void receive(String data) {
        log.info("Event receive: {}", data);
    }
}
