package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "CourseEnrollmentProgress",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_enrollment_progress_id")
})
public class CourseEnrollmentProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_enrollment_progress_id", nullable = false)
    private Integer courseEnrollmentProgressId;
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_enrollment_id", nullable=false)
    private CourseEnrollment courseEnrollment;
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_lesson_id", nullable=false)
    private CourseLesson courseLesson;
    @Setter
    @Column(nullable = false)
    private Integer progress;
    @Setter
    @Column(name = "last_done", nullable = false)
    private LocalDateTime lastDone;

    public CourseEnrollmentProgress(){}

    public CourseEnrollmentProgress(CourseEnrollment courseEnrollment, CourseLesson courseLesson, Integer progress, LocalDateTime lastDone){
        this.courseEnrollment = courseEnrollment;
        this.courseLesson = courseLesson;
        this.progress = progress;
        this.lastDone = lastDone;
    }
}
