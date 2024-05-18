package co.com.pichincha.users.application.events;

import co.com.pichincha.users.infrastructure.adapters.inputs.dtos.UserDTO;
import co.com.pichincha.users.infrastructure.events.Event;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRegistered implements Event<UserDTO> {

    private final UserDTO userDTO;

    @Override
    public String name() {
        return "UserRegistered";
    }

    @Override
    public UserDTO data() {
        return userDTO;
    }
}
