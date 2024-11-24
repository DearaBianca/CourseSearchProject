package scp.searchcourseplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scp.searchcourseplatform.models.Comment;
import scp.searchcourseplatform.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByCourseId(String courseId) {
        return commentRepository.findByCourseId(courseId);
    }

    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }
}

