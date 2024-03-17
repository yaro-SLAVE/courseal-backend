package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
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
    private String email;
    @Column(name = "date_created")
    @Setter
    private LocalDateTime dateCreated;
    @Column(name = "can_create_courses")
    @Setter
    private boolean canCreate;

    public User(){}

    public User(String userTag, String userName, String password){
        this.userTag = userTag;
        this.userName = userName;
        this.password = password;
    }
}
