package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.courseal.courseal_backend.models.enums.CoursePermissions;

@Entity
@Getter
@Table(name = "CourseMaintainer",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_maintainer_id"),
        })
public class CourseMaintainer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "course_maintainer_id", nullable = false)
        private Integer courseMaintainerId;
        @Setter
        @ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="course_id", nullable=false)
        private Course course;
        @Setter
        @ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="user_id", nullable=false)
        private User user;
        @Enumerated(EnumType.ORDINAL)
        @Column(nullable = false)
        @Setter
        private CoursePermissions permissions;

        public CourseMaintainer(){}

        public CourseMaintainer(Course course, User user, CoursePermissions permissions){
                this.course = course;
                this.user = user;
                this.permissions = permissions;
        }
}
