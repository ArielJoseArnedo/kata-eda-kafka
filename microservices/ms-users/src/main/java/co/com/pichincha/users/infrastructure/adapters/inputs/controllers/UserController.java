package co.com.pichincha.users.infrastructure.adapters.inputs.controllers;

import co.com.pichincha.users.application.commands.RegisterUserCommand;
import co.com.pichincha.users.infrastructure.adapters.inputs.dtos.UserDTO;
import co.com.pichincha.users.infrastructure.events.CommandProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class UserController implements CommandProcessor {

    private final RegisterUserCommand registerUserCommand;

    @Autowired
    public UserController(RegisterUserCommand registerUserCommand) {
        this.registerUserCommand = registerUserCommand;
    }

    @PostMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserDTO>> register(@RequestBody UserDTO userDTO) {
        return executeCommand(registerUserCommand, userDTO);
    }
}
