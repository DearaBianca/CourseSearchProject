package scp.searchcourseplatform.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import scp.searchcourseplatform.models.Comment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final ElasticsearchClient client;
    private static final String INDEX_NAME = "comments";

    public CommentService(ElasticsearchClient client) {
        this.client = client;
    }

    public void saveComment(Comment comment) throws IOException {
        client.index(i -> i
                .index(INDEX_NAME)
                .document(comment)
        );
    }

    public Comment getCommentById(String id) throws IOException {
        GetResponse<Comment> response = client.get(g -> g
                        .index(INDEX_NAME)
                        .id(id),
                Comment.class
        );
        return response.source();
    }

    public List<Comment> getAllComments() throws IOException {
        var response = client.search(s -> s
                        .index(INDEX_NAME)
                        .query(q -> q.matchAll(m -> m)),
                Comment.class);

        List<Comment> comments = new ArrayList<>();
        response.hits().hits().forEach(hit -> comments.add(hit.source()));
        return comments;
    }

    public List<Comment> getCommentsByCourseId(String courseId) throws IOException {
        var response = client.search(s -> s
                        .index(INDEX_NAME)
                        .query(q -> q
                                .match(m -> m
                                        .field("courseId")
                                        .query(courseId)
                                )
                        ),
                Comment.class);

        List<Comment> results = new ArrayList<>();
        response.hits().hits().forEach(hit -> results.add(hit.source()));
        return results;
    }

    public void deleteComment(String id) throws IOException {
        client.delete(d -> d.index(INDEX_NAME).id(id));
    }
}
