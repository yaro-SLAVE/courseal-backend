package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "lesson_token",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "lesson_token_id")
        })
public class LessonToken {
    @Id
    @Column(name = "lesson_token_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonTokenId;
    @Column(name = "lesson_token", nullable = false)
    @Setter
    private String lessonToken;
    @Setter
    @Column(name = "isActive", nullable = false)
    private Boolean isActive;
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_lesson_id", nullable=false)
    private CourseLesson courseLesson;
    @Setter
    @Column(name = "tasks", nullable = false)
    private List<Integer> tasksId;

    public LessonToken(String lessonToken, Boolean isActive, CourseLesson courseLesson, List<Integer> tasks) {
        this.lessonToken = lessonToken;
        this.isActive = isActive;
        this.courseLesson = courseLesson;
        this.tasksId = tasks;
    }
}
