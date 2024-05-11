package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.models.UserActivity;
import online.courseal.courseal_backend.repositories.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserActivityService {
    @Autowired
    UserActivityRepository userActivityRepository;

    public Optional<UserActivity> findByUserActivityId(Integer userActivityId) {
        return userActivityRepository.findByUserActivityId(userActivityId);
    }

    public List<UserActivity> findByUserAndDay(User user, LocalDate day) {
        return userActivityRepository.findByUserAndDay(user, day);
    }

    public Boolean existsByUserAndDay(User user, LocalDate day) {
        return userActivityRepository.existsByUserAndDay(user, day);
    }

    public void createUserActivity(User user, LocalDate day, Integer xp) {
        UserActivity userActivity = new UserActivity(
                user,
                day,
                xp
        );

        userActivityRepository.save(userActivity);
    }

    public void save(UserActivity userActivity) {
        userActivityRepository.save(userActivity);
    }

    public void delete(UserActivity userActivity) {
        userActivityRepository.delete(userActivity);
    }
}
