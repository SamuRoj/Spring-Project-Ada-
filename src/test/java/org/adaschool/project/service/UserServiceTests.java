package org.adaschool.project.service;

import org.adaschool.project.dto.UserDTO;
import org.adaschool.project.exception.UserNotFoundException;
import org.adaschool.project.model.UserEntity;
import org.adaschool.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void UserService_GetAllUsers(){
        UserEntity user1 = UserEntity.builder()
                .name("User")
                .email("admin@admin.com")
                .lastName("Admin")
                .password("123")
                .build();

        UserEntity user2 = UserEntity.builder()
                .name("User2")
                .email("admin@admin.com")
                .lastName("Admin")
                .password("123")
                .build();

        List<UserEntity> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> usersRetrieved = userService.getAllUsers();

        Assertions.assertNotNull(usersRetrieved);
        Assertions.assertEquals(2, usersRetrieved.size());
    }

    @Test
    public void UserService_GetUserById(){
        UserEntity user = UserEntity.builder()
                .id("1")
                .name("User")
                .email("admin@admin.com")
                .lastName("Admin")
                .password("123")
                .build();

        when(userRepository.findById(Mockito.any(String.class)))
                .thenReturn(Optional.ofNullable(user));

        UserEntity retrievedUser = userService.getUserById("1");

        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals("1", retrievedUser.getId());
        Assertions.assertEquals("User", retrievedUser.getName());
        Assertions.assertEquals("admin@admin.com", retrievedUser.getEmail());
        Assertions.assertEquals("Admin", retrievedUser.getLastName());
        Assertions.assertEquals(UserEntity.class, retrievedUser.getClass());
    }

    @Test
    public void UserService_GetUserByIdNotFound(){
        when(userRepository.findById("1"))
                .thenReturn(Optional.empty());

        String id = "1";
        Assertions.assertThrows(UserNotFoundException.class,
                                () -> userService.getUserById(id),
                                "user with ID: 1 not found");
    }

    @Test
    public void UserService_GetUserByEmail(){
        UserEntity user = UserEntity.builder()
                .id("1")
                .name("User")
                .email("admin@admin.com")
                .lastName("Admin")
                .password("123")
                .build();

        when(userRepository.findByEmail(Mockito.any(String.class)))
                .thenReturn(Optional.ofNullable(user));

        UserEntity retrievedUser = userService.getUserByEmail("admin@admin.com").get();

        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals("1", retrievedUser.getId());
        Assertions.assertEquals("User", retrievedUser.getName());
        Assertions.assertEquals("admin@admin.com", retrievedUser.getEmail());
        Assertions.assertEquals("Admin", retrievedUser.getLastName());
        Assertions.assertEquals(UserEntity.class, retrievedUser.getClass());
    }

    @Test
    public void UserService_GetUserByEmailNotFound(){
        when(userRepository.findByEmail("user@user.com"))
                .thenReturn(Optional.empty());

        String email = "user@user.com";
        Assertions.assertThrows(UserNotFoundException.class,
                () -> userService.getUserByEmail(email),
                "user with ID: user@user.com not found");
    }

    @Test
    public void UserService_SaveUser(){
        UserEntity user = UserEntity.builder()
                        .name("User")
                        .email("admin@admin.com")
                        .lastName("Admin")
                        .password("123")
                        .build();
        UserDTO userDTO = UserDTO.builder()
                        .name("User")
                        .email("admin@admin.com")
                        .lastName("Admin")
                        .password("123")
                        .build();

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);

        UserEntity savedUser = userService.saveUser(userDTO);
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(savedUser.getName(), userDTO.getName());
        Assertions.assertEquals(savedUser.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(savedUser.getLastName(), userDTO.getLastName());
    }

    @Test
    public void UserService_UpdateUser(){
        UserEntity user = UserEntity.builder()
                .id("1")
                .name("User")
                .email("admin@admin.com")
                .lastName("Admin")
                .password("123")
                .build();

        UserEntity update = UserEntity.builder()
                .name("Name")
                .email("user@user.com")
                .lastName("User")
                .password("456")
                .build();

        when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(update);

        UserDTO userDTO = UserDTO.builder()
                .name("Name")
                .email("user@user.com")
                .lastName("User")
                .password("456")
                .build();

        UserEntity updatedUser = userService.updateUser("1", userDTO);
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(updatedUser.getName(), userDTO.getName());
        Assertions.assertEquals(updatedUser.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(updatedUser.getLastName(), userDTO.getLastName());
    }
}
