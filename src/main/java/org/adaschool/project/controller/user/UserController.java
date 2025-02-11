package org.adaschool.project.controller.user;

import org.adaschool.project.dto.UserDTO;
import org.adaschool.project.model.User;
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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.saveUser(userDTO);
        URI createdUserUri = URI.create("/v1/users/" + createdUser.getId());
        return ResponseEntity.created(createdUserUri).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }
}
