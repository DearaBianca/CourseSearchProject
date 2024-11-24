package scp.searchcourseplatform.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import scp.searchcourseplatform.models.Course;
import scp.searchcourseplatform.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Manage Courses in Elasticsearch")
public class CourseController {

    @Autowired
    private CourseService courseService; // Un serviciu pentru manipularea logicii cursurilor

    @PostMapping
    @Operation(summary = "Add a new course", description = "Add a new course to the Elasticsearch index")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a course by ID", description = "Retrieve course details using its ID")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id)
                .map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    @Operation(summary = "Search courses", description = "Search for courses by keyword in name, description, or content")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String keyword) {
        return new ResponseEntity<>(courseService.searchCourses(keyword), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a course by ID", description = "Delete a specific course using its ID")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

