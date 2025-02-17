package cz.upce.fei.nnpiacv.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private long id;
    private String email;
    private String passsword;
}
