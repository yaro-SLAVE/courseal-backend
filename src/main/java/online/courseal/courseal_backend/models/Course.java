package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Courses",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "course_id"),
        @UniqueConstraint(columnNames = "course_name")
        })
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int courseId;
    @Column(name = "course_name")
    @Getter
    @Setter
    private String courseName;
    @Column(name = "course_description")
    @Getter
    @Setter
    private String courseDescription;

    public Course(){}

    public Course(String courseName, String courseDescription){
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }
}
