package online.courseal.courseal_backend.service;

import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repository.UserRepository;
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
    public UserDetails loadUserByUsername(String userTag) throws UsernameNotFoundException {
        User user = userRepository
                .findByUserTag(userTag)
                .orElseThrow(() -> new UsernameNotFoundException("User no found with userTag: " + userTag));
        return UserDetailsImpl.build(user);
    }
}
