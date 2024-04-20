package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users_activity",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_activity_id")
        })
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_activity_id", nullable = false)
    private int userActivityId;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @Setter
    @Column(nullable = false)
    private LocalDate day;
    @Setter
    @Column(nullable = false)
    private int xp;

    public UserActivity(User user, LocalDate day, int xp){
        this.user = user;
        this.day = day;
        this.xp = xp;
    }
}
