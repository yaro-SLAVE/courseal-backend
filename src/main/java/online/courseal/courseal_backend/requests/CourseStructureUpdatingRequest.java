package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseStructureUpdatingRequest {
    @JsonProperty()
    private List<List<CourseStructureUpdatingRequest>> data;
}
