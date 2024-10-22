package common.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {

    private int id;
    private String courseName;
    private String courseCode;
    private String courseCategory;
    private String coursePrice;
    private String courseLastUpdated;
    private String courseCapacity;
    private String courseLevel;
    private String courseStartDate;
    private String courseEndDate;
    private String courseStartTime;
    private String courseEndTime;



    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Course course = (Course) object;
        return Objects.equals(courseName, course.courseName) && Objects.equals(courseCode, course.courseCode) && Objects.equals(courseCategory, course.courseCategory) && Objects.equals(coursePrice, course.coursePrice) && Objects.equals(courseLastUpdated, course.courseLastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseCode, courseCategory, coursePrice, courseLastUpdated);
    }


}
