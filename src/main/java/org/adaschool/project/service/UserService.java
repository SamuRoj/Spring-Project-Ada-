package org.adaschool.project.service;

import org.adaschool.project.dto.UserDTO;
import org.adaschool.project.exception.UserNotFoundException;
import org.adaschool.project.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private Map<Integer, User> users = new HashMap<>();

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    public User getUserById(Integer id){
        if(users.get(id) != null) return users.get(id);
        throw new UserNotFoundException(id);
    }

    public User saveUser(UserDTO userDTO){
        User newUser = new User(users.size() + 1, userDTO);
        users.put(newUser.getId(), newUser);
        return newUser;
    }

    public User updateUser(Integer id, UserDTO userDTO){
        User updateUser = users.get(id);
        updateUser.setName(userDTO.getName());
        updateUser.setLastName(userDTO.getLastName());
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setPassword(userDTO.getPassword());
        users.put(id, updateUser);
        return updateUser;
    }

    public void deleteUser(Integer id) throws UserNotFoundException{
        if(!users.containsKey(id)) throw new UserNotFoundException(id);
        users.remove(id);
    }
}
