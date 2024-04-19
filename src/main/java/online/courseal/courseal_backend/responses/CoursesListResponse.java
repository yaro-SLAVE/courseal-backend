package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.models.enums.CoursePermissions;

@AllArgsConstructor
public class CoursesListResponse {
    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("maintainer_permissions")
    @Enumerated(EnumType.STRING)
    private CoursePermissions maintainerPermissions;
}
