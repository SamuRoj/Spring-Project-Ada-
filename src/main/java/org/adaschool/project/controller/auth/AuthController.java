package org.adaschool.project.controller.auth;

import org.adaschool.project.exception.InvalidCredentialsException;
import org.adaschool.project.model.UserEntity;
import org.adaschool.project.service.UserService;
import org.adaschool.project.security.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final UserService userService;

    private final JWTUtil jwtUtil;

    public AuthController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDto) {
        Optional<UserEntity> optionalUser = userService.getUserByEmail(loginDto.getUsername());
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(), userEntity.getPassword())){
                TokenDTO tokenDTO = jwtUtil.generateToken(loginDto.getUsername());
                return ResponseEntity.ok(tokenDTO);
            }
            else{
                throw new InvalidCredentialsException();
            }
        }
        else{
            throw new InvalidCredentialsException();
        }
    }
}
