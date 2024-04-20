package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "Course",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "course_id"),
        @UniqueConstraint(columnNames = "course_name")
        })
public class Course {
    @Id
    @Column(name = "course_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    @Column(name = "course_name", nullable = false)
    @Setter
    private String courseName;
    @Column(name = "course_description", nullable = false)
    @Setter
    private String courseDescription;
    @Column(name="last_updated_lessons", nullable = false)
    @Setter
    private LocalDateTime lastUpdatedLessons;
    @Column(name="last_updated_tasks", nullable = false)
    @Setter
    private LocalDateTime lastUpdatedTasks;
    @Column(name="last_updated_structure", nullable = false)
    @Setter
    private LocalDateTime lastUpdatedStructure;
    @OneToMany(mappedBy = "Course", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseLesson> courseLessons;
    @OneToMany(mappedBy = "Course", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseEnrollment> courseEnrollments;
    @OneToMany(mappedBy = "Course", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CourseTask> courseTasks;
    @OneToMany(mappedBy = "Course", fetch=FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<CourseMaintainer> courseMaintainers;

    public Course(String courseName, String courseDescription){
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }
}
