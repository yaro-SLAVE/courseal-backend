package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name="CourseTasks",
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
    private String task;

    public CourseTask(){}

    public CourseTask(Course course, String taskName, String task){
        this.course = course;
        this.taskName = taskName;
        this.task = task;
    }
}
