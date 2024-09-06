package co.com.pichincha.users.infrastructure.events;

public interface Event <T> {

    String getName();

    T getData();
}
