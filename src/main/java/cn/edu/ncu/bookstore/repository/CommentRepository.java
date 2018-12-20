package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Book;
import cn.edu.ncu.bookstore.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    //通过book查询其所有评论，根据时间降序排序
    @Query("select c from Comment c where c.book=?1 order by c.comment_time desc")
    List<Comment> findCommentsByBook(Book book);

}
