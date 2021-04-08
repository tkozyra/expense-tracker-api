package pl.tkozyra.expensetrackerapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.tkozyra.expensetrackerapi.entity.User;
import pl.tkozyra.expensetrackerapi.repository.UserRepository;
import pl.tkozyra.expensetrackerapi.service.UserDetailsImpl;

import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {

    UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public Optional<User> findOne(@PathVariable(value = "id") Long id) {
        return userRepository.findById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    public Optional<User> findCurrentUser(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository.findById(userDetails.getId());
    }
}
