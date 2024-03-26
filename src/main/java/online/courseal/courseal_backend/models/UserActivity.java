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

    @ManyToOne
    @Getter
    private User user;

    @Getter
    @Setter
    private LocalDate day;
    @Getter
    @Setter
    private int xp;

    public UserActivity(){}

    public UserActivity(User user, LocalDate day, int xp){
        this.user = user;
        this.day = day;
        this.xp = xp;
    }
}
