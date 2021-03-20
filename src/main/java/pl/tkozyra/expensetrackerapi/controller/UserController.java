package pl.tkozyra.expensetrackerapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.tkozyra.expensetrackerapi.dto.UserDto;
import pl.tkozyra.expensetrackerapi.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @CrossOrigin
    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

}
