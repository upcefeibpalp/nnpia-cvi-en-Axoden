package cz.upce.fei.nnpiacv.domain;

import lombok.*;

@AllArgsConstructor
@Data
public class User {
    private long id;
    private String email;
    private String passsword;
}
