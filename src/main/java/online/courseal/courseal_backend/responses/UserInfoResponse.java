package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.responses.data.CoursesEnrollmentsData;
import online.courseal.courseal_backend.responses.data.CoursesMaintainersData;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class UserInfoResponse {
    @JsonProperty("usertag")
    private String usertag;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("date_created")
    private LocalDateTime dateCreated;
    @JsonProperty("can_create_courses")
    private boolean canCreateCourses;
    @JsonProperty("xp")
    private Integer xp;
    @JsonProperty("profile_image_url")
    private String profileImageUrl;
    @JsonProperty("courses")
    private List<CoursesEnrollmentsData> coursesEnrollmentsDataList;
    @JsonProperty("courses_maintainer")
    private List<CoursesMaintainersData> coursesMaintainersDataList;
}
