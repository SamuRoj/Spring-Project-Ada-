package org.adaschool.project.service;

import org.adaschool.project.model.UserEntity;
import org.adaschool.project.repository.UserRepository;
import org.adaschool.project.dto.UserDTO;
import org.adaschool.project.exception.UserNotFoundException;
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

    public List<UserEntity> getAllUsers(){
        return new ArrayList<>(userRepository.findAll());
    }

    public UserEntity getUserById(String id){
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent()) return user.get();
        throw new UserNotFoundException(id);
    }

    public Optional<UserEntity> getUserByEmail(String email){
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isPresent()) return user;
        throw new UserNotFoundException(email);
    }

    public UserEntity saveUser(UserDTO userDTO){
        UserEntity newUserEntity = new UserEntity(userDTO);
        userRepository.save(newUserEntity);
        return newUserEntity;
    }

    public UserEntity updateUser(String id, UserDTO userDTO){
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException(id);
        UserEntity updateUserEntity = user.get();
        updateUserEntity.update(userDTO);
        userRepository.save(updateUserEntity);
        return updateUserEntity;
    }

    public void deleteUser(String id) throws UserNotFoundException{
        if(!userRepository.existsById(id)) throw new UserNotFoundException(id);
        userRepository.deleteById(id);
    }
}
