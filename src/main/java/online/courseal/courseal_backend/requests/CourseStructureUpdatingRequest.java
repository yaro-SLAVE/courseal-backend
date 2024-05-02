package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import online.courseal.courseal_backend.requests.data.CourseStructureUpdatingData;

import java.util.List;

@Getter
public class CourseStructureUpdatingRequest {
    @JsonProperty()
    private List<List<CourseStructureUpdatingData>> data;
}
