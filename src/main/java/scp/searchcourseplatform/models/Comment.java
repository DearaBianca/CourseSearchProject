package scp.searchcourseplatform.models;

import lombok.Data;


@Data
//@Document(indexName = "course_comments")
public class Comment {
    private String id;
    private String courseId;
    private String userId;
    private String comment;
    private String timestamp;
    private float rating;

}
