package co.com.pichincha.users.application.events;

import co.com.pichincha.users.infrastructure.adapters.inputs.dtos.UserDTO;
import co.com.pichincha.users.infrastructure.events.Event;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRegistered implements Event<UserDTO> {

    private final UserDTO data;

    @Override
    public String getName() {
        return "UserRegistered";
    }

    @Override
    public UserDTO getData() {
        return data;
    }
}
