package de.oks.g52shop.service.interfaces;

import de.oks.g52shop.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(User user);
}
