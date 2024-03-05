package online.courseal.courseal_backend.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import online.courseal.courseal_backend.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1l;

    private int user_id;
    private String usertag;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private LocalDateTime date_created;
    private boolean can_create_courses;

    public UserDetailsImpl(int user_id, String usertag, String username, String password,
                           String email, LocalDateTime date_created,
                           boolean can_create_courses){
        this.user_id = user_id;
        this.usertag = usertag;
        this.username = username;
        this.password = password;
        this.email = email;
        this.date_created = date_created;
        this.can_create_courses = can_create_courses;
    }

    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(
                user.getUserId(),
                user.getUserTag(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getDateCreated(),
                user.getCanCreate());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getUsertag(){
        return this.usertag;
    }

    public String getEmail(){
        return this.email;
    }

    public LocalDateTime getDate_created(){
        return this.date_created;
    }

    public boolean getCan_created_courses(){
        return this.can_create_courses;
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
