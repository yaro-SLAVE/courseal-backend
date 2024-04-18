package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCourseResponse {
    @JsonProperty("course_id")
    private Integer courseId;

    public CreateCourseResponse(Integer courseId) {
        this.courseId = courseId;
    }
}
