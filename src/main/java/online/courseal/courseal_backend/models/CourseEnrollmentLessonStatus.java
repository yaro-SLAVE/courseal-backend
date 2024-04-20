package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "course_enrollment_lesson_status",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_enrollment_lesson_status_id")
})
public class CourseEnrollmentLessonStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_enrollment_lesson_status_id", nullable = false)
    private Integer courseEnrollmentLessonStatusId;
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="course_enrollment_id", nullable=false)
    private CourseEnrollment courseEnrollment;
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="course_lesson_id", nullable=false)
    private CourseLesson courseLesson;
    @Setter
    @Column(nullable = false)
    private Integer progress;
    @Setter
    @Column(name = "last_done", nullable = false)
    private LocalDateTime lastDone;

    public CourseEnrollmentLessonStatus(CourseEnrollment courseEnrollment, CourseLesson courseLesson, Integer progress, LocalDateTime lastDone){
        this.courseEnrollment = courseEnrollment;
        this.courseLesson = courseLesson;
        this.progress = progress;
        this.lastDone = lastDone;
    }
}
