package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "CourseLessonLecture",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "course_lesson_lecture_id")
})
public class CourseLessonLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_lesson_lecture_id", nullable = false)
    private Integer courseLessonLectureId;
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_lesson_id", nullable=false)
    private CourseLesson courseLesson;
    @Setter
    @Column(nullable = false)
    private String lecture;

    public CourseLessonLecture(){}

    public CourseLessonLecture(CourseLesson courseLesson, String lecture){
        this.courseLesson = courseLesson;
        this.lecture = lecture;
    }
}
