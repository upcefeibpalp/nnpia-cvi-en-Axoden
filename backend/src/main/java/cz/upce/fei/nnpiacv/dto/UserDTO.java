package cz.upce.fei.nnpiacv.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
