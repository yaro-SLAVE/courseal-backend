package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    @Getter
    private int userId;
    @Column(name = "usertag")
    @Getter
    @Setter
    private String userTag;
    @Column(name = "username")
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String email;
    @Column(name = "date_created")
    @Getter
    @Setter
    private LocalDateTime dateCreated;
    @Column(name = "can_create_courses")
    @Getter
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
