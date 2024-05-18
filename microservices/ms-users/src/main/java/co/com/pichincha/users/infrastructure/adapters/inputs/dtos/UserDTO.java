package co.com.pichincha.users.infrastructure.adapters.inputs.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor
public class UserDTO {

    private UUID id;
    private String name;
}
