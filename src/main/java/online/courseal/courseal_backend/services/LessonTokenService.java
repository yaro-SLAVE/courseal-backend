package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.CourseLesson;
import online.courseal.courseal_backend.models.LessonToken;
import online.courseal.courseal_backend.repositories.LessonTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LessonTokenService {
    @Autowired
    LessonTokenRepository lessonTokenRepository;

    public Optional<LessonToken> findByLessonTokenId(Integer lessonTokenId) {
        return lessonTokenRepository.findByLessonTokenId(lessonTokenId);
    }

    public Optional<LessonToken> findByLessonToken(String lessonToken) {
        return lessonTokenRepository.findByLessonToken(lessonToken);
    }

    public LessonToken createLessonToken(CourseLesson courseLesson) {
        List<Integer> tasksId = new ArrayList<>();

        LessonToken lessonToken = new LessonToken(
                UUID.randomUUID().toString(),
                true,
                courseLesson,
                tasksId
        );

        lessonToken = lessonTokenRepository.save(lessonToken);
        return lessonToken;
    }

    public void save(LessonToken lessonToken) {
        lessonTokenRepository.save(lessonToken);
    }

    public void delete(LessonToken lessonToken) {
        lessonTokenRepository.delete(lessonToken);
    }
}
