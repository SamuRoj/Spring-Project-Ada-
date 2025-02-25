package org.adaschool.project.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.adaschool.project.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@Builder
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;

    public UserEntity(){
    }

    public UserEntity(String id, String name, String lastName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public UserEntity(UserDTO userDTO){
        this.id = null;
        this.name = userDTO.getName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.password = new BCryptPasswordEncoder().encode(userDTO.getPassword());
    }

    public void update(UserDTO userDTO){
        this.name = userDTO.getName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.password = new BCryptPasswordEncoder().encode(userDTO.getPassword());
    }
}
