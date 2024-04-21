package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "course_enrollment",
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
    @Column(nullable = false)
    private Integer rating;
    @OneToMany(mappedBy = "courseEnrollment", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseEnrollmentTaskStatus> courseEnrollmentTaskStatuses;
    @OneToMany(mappedBy = "courseEnrollment", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseEnrollmentLessonStatus> courseEnrollmentLessonStatuses;

    public CourseEnrollment(Course course, User user, Integer xp, Integer rating){
        this.course = course;
        this.user = user;
        this.xp = xp;
        this.rating = rating;
    }
}
