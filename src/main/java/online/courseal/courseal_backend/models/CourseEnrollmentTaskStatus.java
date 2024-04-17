package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "CourseEnrollmentStatus",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_enrollment_task_status_id")
})
public class CourseEnrollmentTaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_enrollment_task_status_id", nullable = false)
    private Integer courseEnrollmentTaskStatus_id;
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_enrollment_id", nullable=false)
    private CourseEnrollment courseEnrollment;
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_task_id", nullable=false)
    private CourseTask courseTask;
    @Setter
    @Column(name = "times_done", nullable = false)
    private Integer timesDone;
    @Setter
    @Column(name = "times_failed", nullable = false)
    private Integer timesFailed;

    public CourseEnrollmentTaskStatus(){}

    public CourseEnrollmentTaskStatus(CourseEnrollment courseEnrollment, CourseTask courseTask, Integer timesDone, Integer timesFailed){
        this.courseEnrollment = courseEnrollment;
        this.courseTask = courseTask;
        this.timesDone = timesDone;
        this.timesFailed = timesFailed;
    }
}
