package org.adaschool.project.service;

import org.adaschool.project.UserRepository;
import org.adaschool.project.dto.UserDTO;
import org.adaschool.project.exception.UserNotFoundException;
import org.adaschool.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(userRepository.findAll());
    }

    public User getUserById(String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) return user.get();
        throw new UserNotFoundException(id);
    }

    public User saveUser(UserDTO userDTO){
        User newUser = new User(userDTO);
        userRepository.save(newUser);
        return newUser;
    }

    public User updateUser(String id, UserDTO userDTO){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException(id);
        User updateUser = user.get();
        updateUser.update(userDTO);
        userRepository.save(updateUser);
        return updateUser;
    }

    public void deleteUser(String id) throws UserNotFoundException{
        if(!userRepository.existsById(id)) throw new UserNotFoundException(id);
        userRepository.deleteById(id);
    }
}
