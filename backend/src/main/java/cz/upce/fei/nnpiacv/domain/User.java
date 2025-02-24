package cz.upce.fei.nnpiacv.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Data
@Entity
@Table(name = "app_user")
@NoArgsConstructor
public class User {
    @Id
    private long id;
    @Column(unique = true)
    private String email;
    private String passsword;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
