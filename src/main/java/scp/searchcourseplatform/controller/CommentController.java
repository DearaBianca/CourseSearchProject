package scp.searchcourseplatform.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import scp.searchcourseplatform.models.Comment;
import scp.searchcourseplatform.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comments", description = "Manage Comments for Courses")
public class CommentController {

    @Autowired
    private CommentService commentService; // Un serviciu pentru manipularea logicii comentariilor

    @PostMapping
    @Operation(summary = "Add a new comment", description = "Add a new comment to a course in Elasticsearch")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get comments for a course", description = "Retrieve all comments for a specific course")
    public ResponseEntity<List<Comment>> getCommentsByCourse(@PathVariable String courseId) {
        return new ResponseEntity<>(commentService.getCommentsByCourseId(courseId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a comment by ID", description = "Delete a specific comment using its ID")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

