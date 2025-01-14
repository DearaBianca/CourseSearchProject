package scp.searchcourseplatform.models;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private List<Comment> comments;

}
