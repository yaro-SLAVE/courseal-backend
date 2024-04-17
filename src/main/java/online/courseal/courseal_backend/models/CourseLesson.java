package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "CourseLesson",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_lesson_id")
})
public class CourseLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_lesson_id", nullable = false)
    private Integer courseLessonId;
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_id", nullable=false)
    private Course course;
    @Setter
    @Column(name = "lesson_name", nullable = false)
    private String lessonName;
    @Setter
    @Column(name = "lesson_level", nullable = true)
    private Integer lessonLevel;
    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "lesson_type", nullable = false)
    private LessonType lessonType;
    @Setter
    @Column(name = "progress_needed", nullable = false)
    private Integer progressNeeded;
    @Setter
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    public CourseLesson(){}

    public CourseLesson(Course course, String lessonName, Integer lessonLevel, LessonType lessonType, Integer progressNeeded, LocalDateTime lastUpdated){
        this.course = course;
        this.lessonName = lessonName;
        this.lessonLevel = lessonLevel;
        this.lessonType = lessonType;
        this.progressNeeded = progressNeeded;
        this.lastUpdated = lastUpdated;
    }
}
