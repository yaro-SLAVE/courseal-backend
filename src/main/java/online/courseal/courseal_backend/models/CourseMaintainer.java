package online.courseal.courseal_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "CoursesMaintaiers",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "course_maintainer_id")
        })
public class CourseMaintainer {
}
