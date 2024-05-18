package co.com.pichincha.openbanking.infrastructure.events;

public interface Event <T> {

    String name();

    T data();
}
