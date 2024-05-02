package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.responses.data.CourseStructureData;

import java.util.List;

@AllArgsConstructor
public class CourseStructureListResponse {
    @JsonProperty()
    private List<CourseStructureData> data;
}
