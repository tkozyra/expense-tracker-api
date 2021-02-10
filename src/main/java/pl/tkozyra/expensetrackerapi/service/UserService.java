package pl.tkozyra.expensetrackerapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.tkozyra.expensetrackerapi.exception.UserNotFoundException;
import pl.tkozyra.expensetrackerapi.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
