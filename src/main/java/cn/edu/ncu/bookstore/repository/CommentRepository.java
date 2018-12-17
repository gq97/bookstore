package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
