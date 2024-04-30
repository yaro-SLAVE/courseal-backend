package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.courseal.courseal_backend.coursedata.editorjs.EditorJSContent;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    @JdbcTypeCode(SqlTypes.JSON)
    private EditorJSContent lecture;

    public CourseLessonLecture(CourseLesson courseLesson, EditorJSContent lecture){
        this.courseLesson = courseLesson;
        this.lecture = lecture;
    }
}
