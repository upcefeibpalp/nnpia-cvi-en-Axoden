package cz.upce.fei.nnpiacv.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Data
@Entity
@Table(name = "app_user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String passsword;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
