package cz.upce.fei.nnpiacv.domain;

import jakarta.persistence.*;
import lombok.*;

// In User.java
@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String password;

    // Add the active field with column definition and default value
    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    private boolean active = true;

    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
        this.active = true;
    }
}
