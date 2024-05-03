package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.coursedata.editorjs.EditorJSContent;
import online.courseal.courseal_backend.models.CourseLesson;
import online.courseal.courseal_backend.models.CourseLessonLecture;
import online.courseal.courseal_backend.repositories.CourseLessonLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseLessonLectureService {
    @Autowired
    CourseLessonLectureRepository courseLessonLectureRepository;

    public Optional<CourseLessonLectureRepository> findByCourseLessonLectureId(Integer courseLessonLectureId) {
        return courseLessonLectureRepository.findByCourseLessonLectureId(courseLessonLectureId);
    }

    public CourseLessonLecture createCourseLessonLecture(CourseLesson courseLesson, EditorJSContent lecture) {
        CourseLessonLecture courseLessonLecture = new CourseLessonLecture(
                courseLesson,
                lecture
        );

        courseLessonLecture = courseLessonLectureRepository.save(courseLessonLecture);

        return courseLessonLecture;
    }

    public void save(CourseLessonLecture courseLessonLecture) {
        courseLessonLectureRepository.save(courseLessonLecture);
    }

    public void delete(CourseLessonLecture courseLessonLecture) {
        courseLessonLectureRepository.delete(courseLessonLecture);
    }
}
