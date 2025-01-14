package scp.searchcourseplatform.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scp.searchcourseplatform.models.Comment;
import scp.searchcourseplatform.models.Course;
import scp.searchcourseplatform.service.CommentService;
import scp.searchcourseplatform.service.CourseService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Manage Courses in Elasticsearch")
public class CommentsController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/{courseId}/comments")
    @Operation(summary = "Add a new comment", description = "Add a new comment to a course in Elasticsearch")
    public ResponseEntity<String> addComment(@RequestBody Comment comment) throws IOException {
        commentService.saveComment(comment);
        return ResponseEntity.ok("Comment added successfully");
    }

    @GetMapping("/{courseId}/comments")
    @Operation(summary = "Get comments for a course", description = "Retrieve all comments for a specific course")
    public ResponseEntity<List<Comment>> getCommentsByCourseId(@PathVariable String courseId) throws IOException {
        return ResponseEntity.ok(commentService.getCommentsByCourseId(courseId));
    }
    @DeleteMapping("/{courseId}/comments/{commentId}")
    @Operation(summary = "Delete a comment by ID", description = "Delete a specific comment using its ID")
    public ResponseEntity<String> deleteComment(@PathVariable String id) throws IOException {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
