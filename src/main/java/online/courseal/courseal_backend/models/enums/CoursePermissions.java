package online.courseal.courseal_backend.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CoursePermissions {
    @JsonProperty("none") NONE,
    @JsonProperty("full") FULL,
    @JsonProperty("edit_course") EDIT_COURSE,
    @JsonProperty("edit_lessons") EDIT_LESSONS
}
