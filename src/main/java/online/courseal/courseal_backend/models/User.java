package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "usertag")
    @Setter
    private String userTag;
    @Column(name = "username")
    @Setter
    private String userName;
    @Setter
    private String password;
    @Setter
    private String email;
    @Column(name = "date_created")
    @Setter
    private LocalDateTime dateCreated;
    @Column(name = "can_create_courses")
    @Setter
    private boolean canCreate;

    public User(){}

    public User(String userTag, String userName, String password, String email, LocalDateTime dateCreated, boolean canCreate){
        this.userTag = userTag;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dateCreated = dateCreated;
        this.canCreate = canCreate;
    }
}
