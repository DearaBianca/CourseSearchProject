package scp.searchcourseplatform.repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import scp.searchcourseplatform.models.Course;

import java.util.List;

public interface CourseRepository extends ElasticsearchRepository<Course, String> {
    List<Course> findByContentContaining(String keyword);
}

