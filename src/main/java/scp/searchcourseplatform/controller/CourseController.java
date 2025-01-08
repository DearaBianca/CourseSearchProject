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

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Manage Courses in Elasticsearch")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Operation(summary = "Add a new course", description = "Add a new course to the Elasticsearch index")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a course by ID", description = "Retrieve course details using its ID")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") String id) {
        return courseService.getCourseById(id)
                .map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @Operation(summary = "Get all courses", description = "Retrieve all courses from Elasticsearch")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    @Operation(summary = "Search courses", description = "Search for courses by keyword in name, description, or content")
    public ResponseEntity<List<Course>> searchCourses(@PathVariable("keyword") String keyword) {
        return new ResponseEntity<>(courseService.searchCourses(keyword), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a course by ID", description = "Delete a specific course using its ID")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") String id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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
