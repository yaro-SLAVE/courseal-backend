package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.courseal.courseal_backend.repositories.UserRepository;

@Entity
@Getter
@Table(name = "CourseEnrollment",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_enrollment_id")
})
public class CourseEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_enrollment_id", nullable = false)
    private Integer courseEnrollmentId;
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_id", nullable=false)
    private Course course;
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @Setter
    @Column(nullable = false)
    private Integer xp;
    @Setter
    @Column(nullable = true)
    private Integer rating;

    public CourseEnrollment(){}

    public CourseEnrollment(Course course, User user, Integer xp, Integer rating){
        this.course = course;
        this.user = user;
        this.xp = xp;
        this.rating = rating;
    }
}
