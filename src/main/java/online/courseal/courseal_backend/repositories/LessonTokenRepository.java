package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.LessonToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonTokenRepository extends JpaRepository<LessonToken, Integer> {
    Optional<LessonToken> findByLessonTokenId(Integer lessonTokenId);

    Optional<LessonToken> findByLessonToken(String lessonToken);
}
