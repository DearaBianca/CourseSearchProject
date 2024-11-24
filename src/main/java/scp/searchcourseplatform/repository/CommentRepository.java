package scp.searchcourseplatform.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import scp.searchcourseplatform.models.Comment;

import java.util.List;

public interface CommentRepository extends ElasticsearchRepository<Comment, String> {
    List<Comment> findByCourseId(String courseId);
}

