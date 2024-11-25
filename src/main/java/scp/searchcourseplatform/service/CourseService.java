package scp.searchcourseplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scp.searchcourseplatform.models.Course;
import scp.searchcourseplatform.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    public List<Course> searchCourses(String keyword) {
        return courseRepository.findByContentContaining(keyword);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
