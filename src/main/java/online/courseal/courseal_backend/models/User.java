package online.courseal.courseal_backend.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Entity
@Table(name = "User_",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "usertag"),
        @UniqueConstraint(columnNames = "email")
    })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "usertag", nullable = false)
    @Setter
    private String userTag;
    @Column(name = "username", nullable = false)
    @Setter
    private String userName;
    @Setter
    @Column(nullable = false)
    private String password;
    @Setter
    @Column(nullable = true)
    private String email;
    @Column(name = "date_created", nullable = false)
    @Setter
    private LocalDateTime dateCreated;
    @Column(name = "can_create_courses", nullable = false)
    @Setter
    private boolean canCreate;

    public User(){}

    public User(String userTag, String userName, String password, LocalDateTime dateCreated){
        this.userTag = userTag;
        this.userName = userName;
        this.password = password;
        this.dateCreated = dateCreated;
    }
}
