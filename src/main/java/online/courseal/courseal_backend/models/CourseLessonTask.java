package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "course_lesson_task",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_lesson_task_id")
})
public class CourseLessonTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_lesson_task_id", nullable = false)
    private Integer courseLessonTaskId;
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="course_lesson_id", nullable=false)
    private CourseLesson courseLesson;
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="course_task_id", nullable=false)
    private CourseTask courseTask;

    public CourseLessonTask(CourseLesson courseLesson, CourseTask courseTask){
        this.courseLesson = courseLesson;
        this.courseTask = courseTask;
    }
}
