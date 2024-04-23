package online.courseal.courseal_backend.responses.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import online.courseal.courseal_backend.models.enums.CoursePermissions;

@Data
@AllArgsConstructor
public class MaintainersData {
    @JsonProperty("usertag")
    private String userTag;
    @JsonProperty("maintainer_permissions")
    @Enumerated(EnumType.STRING)
    private CoursePermissions coursePermissions;
}
