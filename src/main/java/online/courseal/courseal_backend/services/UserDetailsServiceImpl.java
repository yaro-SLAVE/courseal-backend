package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.errors.exceptions.UserNotFoundException;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userTag) throws UserNotFoundException {
        User user = userRepository
                .findByUserTag(userTag)
                .orElseThrow(UserNotFoundException::new);
        return UserDetailsImpl.build(user);
    }
}
