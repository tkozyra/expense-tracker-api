package pl.tkozyra.expensetrackerapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tkozyra.expensetrackerapi.dto.UserDto;
import pl.tkozyra.expensetrackerapi.entity.User;
import pl.tkozyra.expensetrackerapi.mapper.UserMapper;
import pl.tkozyra.expensetrackerapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);

        if(this.userService.userWithGivenUsernameAlreadyExists(user.getUsername())) {
            return ResponseEntity.status(409).body("User with given username already exists.");
        } else if(this.userService.userWithGivenEmailAlreadyExists(user.getEmail())) {
            return ResponseEntity.status(409).body("User with given email already exists.");
        } else {
            this.userService.save(user);
            return ResponseEntity.status(201).body("Registered successfully.");
        }
    }

    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

}
