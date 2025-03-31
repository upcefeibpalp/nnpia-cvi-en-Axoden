package cz.upce.fei.nnpiacv.dto;

import lombok.Data;

// UserResponseDTO.java (or update your existing UserDTO)
@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private String password;
    private boolean active;
}