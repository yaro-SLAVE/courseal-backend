package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseTask;
import online.courseal.courseal_backend.repositories.CourseTaskRepository;
import online.courseal.courseal_backend.coursedata.tasks.CoursealTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseTaskService {
    @Autowired
    CourseTaskRepository courseTaskRepository;

    public Optional<CourseTask> findByCourseTaskId(Integer courseTaskId) {
        return courseTaskRepository.findByCourseTaskId(courseTaskId);
    }

    public Optional<CourseTask> findByCourse(Course course) {
        return courseTaskRepository.findByCourse(course);
    }

    public CourseTask createCourseTask(Course course, String taskName, CoursealTask task) {
        CourseTask courseTask = new CourseTask(course, taskName, task);

        courseTask = courseTaskRepository.save(courseTask);
        return courseTask;
    }

    public void save(CourseTask courseTask) {
        courseTaskRepository.save(courseTask);
    }

    public void delete(CourseTask courseTask) {
        courseTaskRepository.save(courseTask);
    }
}
