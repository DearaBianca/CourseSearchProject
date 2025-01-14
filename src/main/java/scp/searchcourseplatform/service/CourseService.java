package scp.searchcourseplatform.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import org.springframework.stereotype.Service;
import scp.searchcourseplatform.models.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final ElasticsearchClient client;
    private static final String INDEX_NAME = "courses";

    public CourseService(ElasticsearchClient client) {
        this.client = client;
    }

    public Course saveCourse(Course course) throws IOException {
        client.index(i -> i
                .index(INDEX_NAME)
                .id(course.getId())
                .document(course)
        );
        return course;
    }

    public Course getCourseById(String id) throws IOException {
        GetResponse<Course> response = client.get(g -> g
                        .index(INDEX_NAME)
                        .id(id),
                Course.class
        );
        return response.source();
    }

    public List<Course> searchCourses(String keyword) throws IOException {
        var response = client.search(s -> s
                        .index(INDEX_NAME)
                        .query(q -> q
                                .match(m -> m
                                        .field("content")
                                        .query(keyword)
                                )
                        ),
                Course.class
        );

        List<Course> results = new ArrayList<>();
        response.hits().hits().forEach(hit -> results.add(hit.source()));
        return results;
    }

    public void deleteCourse(String id) throws IOException {
        client.delete(d -> d.index(INDEX_NAME).id(id));
    }
}
