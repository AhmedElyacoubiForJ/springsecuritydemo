package edu.yacoubi.springsecuritydemo;

import lombok.Data;
import lombok.NoArgsConstructor;

/* request object class */
@Data
@NoArgsConstructor
class LoginDTO {
    private String username;
    private String password;
}
