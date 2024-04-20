package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "course_lesson_lecture",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_lesson_lecture_id")
})
public class CourseLessonLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_lesson_lecture_id", nullable = false)
    private Integer courseLessonLectureId;
    @Setter
    @OneToOne(optional=false)
    @JoinColumn(name="course_lesson_id", unique=true, nullable=false, updatable=false)
    private CourseLesson courseLesson;
    @Setter
    @Column(nullable = false)
    private String lecture;

    public CourseLessonLecture(CourseLesson courseLesson, String lecture){
        this.courseLesson = courseLesson;
        this.lecture = lecture;
    }
}
