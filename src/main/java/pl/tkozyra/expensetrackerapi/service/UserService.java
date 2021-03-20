package pl.tkozyra.expensetrackerapi.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.tkozyra.expensetrackerapi.dto.UserDto;
import pl.tkozyra.expensetrackerapi.dto.mapper.UserMapper;
import pl.tkozyra.expensetrackerapi.entity.User;
import pl.tkozyra.expensetrackerapi.exception.EmailAlreadyInUseException;
import pl.tkozyra.expensetrackerapi.exception.UserNotFoundException;
import pl.tkozyra.expensetrackerapi.exception.UsernameAlreadyInUseException;
import pl.tkozyra.expensetrackerapi.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserDto save(UserDto userDto) {

        if (userWithGivenUsernameAlreadyExists(userDto.getUsername())) {
            throw new UsernameAlreadyInUseException();
        }
        if (userWithGivenEmailAlreadyExists(userDto.getEmail())) {
            throw new EmailAlreadyInUseException();
        }

        return userMapper.mapToDto(userRepository.save(userMapper.mapToEntity(userDto)));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public boolean userWithGivenUsernameAlreadyExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean userWithGivenEmailAlreadyExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
