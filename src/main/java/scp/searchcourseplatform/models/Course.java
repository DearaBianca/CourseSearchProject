package scp.searchcourseplatform.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "courses")
public class Course {
    @Id
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

}
