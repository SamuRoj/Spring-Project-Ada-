package org.adaschool.project.controller.user;

import org.adaschool.project.dto.UserDTO;
import org.adaschool.project.model.UserEntity;
import org.adaschool.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> userEntities = userService.getAllUsers();
        return ResponseEntity.ok(userEntities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable("id") String id) {
        UserEntity userEntity = userService.getUserById(id);
        return ResponseEntity.ok(userEntity);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO userDTO) {
        UserEntity createdUserEntity = userService.saveUser(userDTO);
        URI createdUserUri = URI.create("/v1/users/" + createdUserEntity.getId());
        return ResponseEntity.created(createdUserUri).body(createdUserEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO) {
        UserEntity updatedUserEntity = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUserEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }
}
