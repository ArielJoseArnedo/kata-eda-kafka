package co.com.pichincha.openbanking.infrastructure.publishers;


import co.com.pichincha.openbanking.infrastructure.events.Event;

public interface Publisher {

    void send(Event<?> event);
}
