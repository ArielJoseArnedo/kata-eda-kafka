package co.com.pichincha.users.infrastructure.publishers;

import co.com.pichincha.users.infrastructure.events.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public interface Publisher {

    ObjectMapper objectMapper = new ObjectMapper();

    void send(Event<?> event);

    @SneakyThrows
    default  String toString(Event<?> event) {
        return objectMapper.writeValueAsString(event);
    }
}
