package org.adaschool.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private final String name;
    private final String lastName;
    private final String email;
    private final String password;

    public UserDTO() {
        this.name = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
    }

    public UserDTO(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserDTO(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = "";
    }
}
