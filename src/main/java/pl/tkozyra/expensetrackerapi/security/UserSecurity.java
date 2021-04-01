package pl.tkozyra.expensetrackerapi.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.tkozyra.expensetrackerapi.service.UserDetailsImpl;

@Component("userSecurity")
public class UserSecurity {
    public boolean hasUserId(Authentication authentication, Long userId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getId().equals(userId);
    }
}
