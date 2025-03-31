package cz.upce.fei.nnpiacv.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull // Přidat validaci
    private Boolean active;
}
