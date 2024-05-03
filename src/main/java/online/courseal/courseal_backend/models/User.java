package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "user_",
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
    private String email;
    @Column(name = "date_created", nullable = false)
    @Setter
    private LocalDateTime dateCreated;
    @Column(name = "can_create_courses", nullable = false)
    @Setter
    private boolean canCreate;
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RefreshToken> refreshToken;
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserActivity> userActivities;
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseEnrollment> courseEnrollments;
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseMaintainer> courseMaintainers;

    public User(String userTag, String userName, String password, LocalDateTime dateCreated, Boolean canCreate){
        this.userTag = userTag;
        this.userName = userName;
        this.password = password;
        this.dateCreated = dateCreated;
        this.canCreate = canCreate;
    }
}
