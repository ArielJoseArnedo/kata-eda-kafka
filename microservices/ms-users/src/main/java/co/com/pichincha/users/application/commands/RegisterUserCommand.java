package co.com.pichincha.users.application.commands;

import co.com.pichincha.users.application.events.UserRegistered;
import co.com.pichincha.users.infrastructure.adapters.inputs.dtos.UserDTO;
import co.com.pichincha.users.infrastructure.events.Command;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Component
public class RegisterUserCommand implements Command<UserDTO, UserDTO, UserRegistered> {


    @Override
    public Mono<Tuple2<UserDTO, UserRegistered>> execute(UserDTO request) {
        return Mono.just(request)
          .map(userDTO -> Tuples.of(userDTO, new UserRegistered(userDTO)));
    }
}
