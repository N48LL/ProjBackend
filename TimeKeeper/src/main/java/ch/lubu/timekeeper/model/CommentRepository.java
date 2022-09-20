package ch.lubu.timekeeper.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    static List<Comment> findByCommentId(Integer id) {
        return findByCommentId(id);
    }
}