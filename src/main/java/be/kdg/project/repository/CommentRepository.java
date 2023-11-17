package be.kdg.project.repository;

import be.kdg.project.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CommentRepository {
    public List<Comment> readComments() {return DataFactory.getComments();}
}
