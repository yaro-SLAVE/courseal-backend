package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.courseal.courseal_backend.models.enums.CoursePermissions;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "course_maintainer",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_maintainer_id"),
        })
public class CourseMaintainer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "course_maintainer_id", nullable = false)
        private Integer courseMaintainerId;
        @Setter
        @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
        @JoinColumn(name="course_id", nullable=false)
        private Course course;
        @Setter
        @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
        @JoinColumn(name="user_id", nullable=false)
        private User user;
        @Enumerated(EnumType.ORDINAL)
        @Column(nullable = false)
        @Setter
        private CoursePermissions permissions;

        public CourseMaintainer(Course course, User user){
                this.course = course;
                this.user = user;
        }
}
