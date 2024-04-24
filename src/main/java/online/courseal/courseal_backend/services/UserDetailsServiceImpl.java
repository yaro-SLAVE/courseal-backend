package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.errors.exceptions.UserNotFoundException;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findByUserTag(String userTag) {
        return userRepository.findByUserTag(userTag);
    }

    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    public boolean existsByUserTag(String userTag) {
        return userRepository.existsByUserTag(userTag);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findByUserTagLike(String userTag) {
        String search = "%" + userTag + "%";
        return userRepository.findByUserTagLike(search);
    }

    @Override
    public UserDetails loadUserByUsername(String userTag) throws UserNotFoundException {
        User user = userRepository
                .findByUserTag(userTag)
                .orElseThrow(UserNotFoundException::new);
        return UserDetailsImpl.build(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
