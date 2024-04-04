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
@Table(name = "Users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "usertag"),
        @UniqueConstraint(columnNames = "email")
    })
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "usertag")
    @Setter
    private String userTag;
    @Column(name = "username")
    @Setter
    private String userName;
    @Setter
    private String password;
    @Setter
    @Nullable
    private String email;
    @Column(name = "date_created")
    @Setter
    private LocalDateTime dateCreated;
    @Column(name = "can_create_courses")
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
