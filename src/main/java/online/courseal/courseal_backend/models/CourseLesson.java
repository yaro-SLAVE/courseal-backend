package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.courseal.courseal_backend.models.enums.LessonType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "course_lesson",
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
    @OneToMany(mappedBy = "courseLesson", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseEnrollmentLessonStatus> courseEnrollmentLessonStatuses;
    @OneToMany(mappedBy = "courseLesson", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseLessonTask> courseLessonTasks;
    @OneToMany(mappedBy = "courseLesson", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<LessonToken> lessonTokens;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "courseLesson")
    private CourseLessonLecture courseLessonLecture;

    public CourseLesson(Course course, String lessonName, Integer lessonLevel, Integer progressNeeded, LocalDateTime lastUpdated, LessonType lessonType){
        this.course = course;
        this.lessonName = lessonName;
        this.lessonLevel = lessonLevel;
        this.lessonType = lessonType;
        this.progressNeeded = progressNeeded;
        this.lastUpdated = lastUpdated;
    }
}
