package pl.tkozyra.expensetrackerapi.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.tkozyra.expensetrackerapi.dto.UserDto;
import pl.tkozyra.expensetrackerapi.entity.User;

/**
 * Mapper class responsible for entity<->dto mapping for users
 */
@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(ModelMapper modelMapper,
                      PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User mapToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return user;
    }

    public UserDto mapToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
