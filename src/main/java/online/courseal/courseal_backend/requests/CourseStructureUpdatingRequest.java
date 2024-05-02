package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import online.courseal.courseal_backend.requests.data.CourseStructureUpdatingData;

import java.util.ArrayList;
import java.util.List;

public class CourseStructureUpdatingRequest extends ArrayList<ArrayList<CourseStructureUpdatingData>> { }
