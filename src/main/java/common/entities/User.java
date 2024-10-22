package common.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString

@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {
    private  String userName;
    private  String email;
    private  String registration;
    private  String type;
    private  String lastLogin;

    public User(String userName) {
        this.userName = userName;
    }
}
