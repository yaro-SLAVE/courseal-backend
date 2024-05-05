package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.responses.data.EnrollmentCourseLessonData;

import java.util.List;

@AllArgsConstructor
public class EnrollmentCourseInfoResponse {
    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("course_description")
    private String courseDescription;
    @JsonProperty("rating")
    private Integer rating;
    @JsonProperty("xp")
    private Integer xp;
    @JsonProperty("lessons")
    List<EnrollmentCourseLessonData> data;
}
