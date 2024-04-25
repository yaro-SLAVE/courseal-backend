package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.courseal.courseal_backend.coursedata.tasks.CoursealTask;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="course_task",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_task_id")
        })
public class CourseTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_task_id", nullable = false)
    private Integer courseTaskId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_id", nullable=false)
    @Setter
    private Course course;
    @Setter
    @Column(name = "task_name", nullable = false)
    private String taskName;
    @Setter
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private CoursealTask task;
    @OneToMany(mappedBy = "courseTask", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseLessonTask> courseLessonTasks;

    public CourseTask(Course course, String taskName, CoursealTask task){
        this.course = course;
        this.taskName = taskName;
        this.task = task;
    }
}
