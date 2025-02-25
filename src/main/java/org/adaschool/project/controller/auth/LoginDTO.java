package org.adaschool.project.controller.auth;

import lombok.Getter;

@Getter
public class LoginDTO {

    private final String username;

    private final String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
