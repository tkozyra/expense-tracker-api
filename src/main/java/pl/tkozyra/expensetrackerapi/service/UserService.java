package pl.tkozyra.expensetrackerapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tkozyra.expensetrackerapi.entity.User;
import pl.tkozyra.expensetrackerapi.exception.UserNotFoundException;
import pl.tkozyra.expensetrackerapi.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
