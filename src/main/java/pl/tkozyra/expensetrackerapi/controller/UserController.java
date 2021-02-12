package pl.tkozyra.expensetrackerapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.tkozyra.expensetrackerapi.dto.UserDto;
import pl.tkozyra.expensetrackerapi.entity.User;
import pl.tkozyra.expensetrackerapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        User user = convertDtoToEntity(userDto);

        if(this.userService.userWithGivenUsernameAlreadyExists(user.getUsername())) {
            return ResponseEntity.status(409).body("User with given username already exists.");
        } else if(this.userService.userWithGivenEmailAlreadyExists(user.getEmail())) {
            return ResponseEntity.status(409).body("User with given email already exists.");
        } else {
            this.userService.save(user);
            return ResponseEntity.status(201).body("Registered successfully.");
        }
    }

    public UserController(ModelMapper modelMapper,
                          PasswordEncoder passwordEncoder,
                          UserService userService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    //entity <-> dto conversion

    private User convertDtoToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return user;
    }
}
