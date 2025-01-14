package scp.searchcourseplatform.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
//@Document(indexName = "courses")
public class Course {

    private String id;
    private String name;
    private String description;
    private String category;
    private String level;
    private String language;
    private String content;
    private Review reviews;
    private String professor;
    private int numberOfStudents;
//    private List<Comment> comments;
}
