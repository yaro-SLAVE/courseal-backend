package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "Courses",
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
    @Column(name="last_updated", nullable = false)
    @Setter
    private LocalDateTime lastUpdated;

    public Course(){}

    public Course(String courseName, String courseDescription){
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }
}
