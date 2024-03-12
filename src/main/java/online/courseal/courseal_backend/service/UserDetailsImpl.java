package online.courseal.courseal_backend.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import online.courseal.courseal_backend.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1l;

    private int userId;
    private String userTag;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private LocalDateTime dateCreated;
    private boolean canCreateCourses;

    public UserDetailsImpl(int userId, String userTag, String username, String password,
                           String email, LocalDateTime dateCreated,
                           boolean canCreateCourses){
        this.userId = userId;
        this.userTag = userTag;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateCreated = dateCreated;
        this.canCreateCourses = canCreateCourses;
    }

    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(
            user.getUserId(),
            user.getUserTag(),
            user.getUserName(),
            user.getPassword(),
            user.getEmail(),
            user.getDateCreated(),
            user.isCanCreate()
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
