package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "UsersActivities",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_activity_id")
        })
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_activity_id")
    @Getter
    private int userActivityId;

    @Column(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "users_activities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Getter
    private int userId;

    @Getter
    @Setter
    private LocalDate day;
    @Getter
    @Setter
    private int xp;

    public UserActivity(){}

    public UserActivity(int userId, LocalDate day, int xp){
        this.userId = userId;
        this.day = day;
        this.xp = xp;
    }
}
