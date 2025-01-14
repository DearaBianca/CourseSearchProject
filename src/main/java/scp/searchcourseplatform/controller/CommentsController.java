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
    private CourseService courseService;

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Operation(summary = "Add a new course", description = "Add a new course to the Elasticsearch index")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) throws IOException {
        System.out.println("entered in addCourse");
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a course by ID", description = "Retrieve course details using its ID")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") String id)  throws IOException{
        return ResponseEntity.ok(courseService.getCourseById(id));

    }

    @GetMapping("/search/{keyword}")
    @Operation(summary = "Search courses", description = "Search for courses by keyword in name, description, or content")
    public ResponseEntity<List<Course>> searchCourses(@PathVariable String keyword) throws IOException {
        return ResponseEntity.ok(courseService.searchCourses(keyword));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a course by ID", description = "Delete a specific course using its ID")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) throws IOException {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted");
    }

    // todo:< Get all courses

    @PostMapping("/{courseId}/comments")
    @Operation(summary = "Add a new comment", description = "Add a new comment to a course in Elasticsearch")
    public ResponseEntity<Comment> addComment(@PathVariable("courseId") String courseId, @RequestBody Comment comment) {
        comment.setCourseId(courseId);
        Comment savedComment = commentService.saveComment(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}/comments")
    @Operation(summary = "Get comments for a course", description = "Retrieve all comments for a specific course")
    public ResponseEntity<List<Comment>> getCommentsByCourse(@PathVariable("courseId") String courseId) {
        return new ResponseEntity<>(commentService.getCommentsByCourseId(courseId), HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}/comments/{commentId}")
    @Operation(summary = "Delete a comment by ID", description = "Delete a specific comment using its ID")
    public ResponseEntity<Void> deleteComment(@PathVariable("courseId") String courseId, @PathVariable("commentId") String commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
