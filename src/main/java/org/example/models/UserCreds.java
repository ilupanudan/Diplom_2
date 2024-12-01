package org.example.models;
import lombok.*;

import static org.example.steps.UserSteps.user;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreds {
   private String email;
   private String password;

    public static UserCreds from(User user) {
        return new UserCreds(user.getEmail(), user.getPassword());
    }
    
}



