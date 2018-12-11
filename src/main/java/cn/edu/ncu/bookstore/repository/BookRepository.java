package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    //通过分类名查询
    @Query("select b from Book b where b.book_category = ?1")
    List<Book> findByBook_category(String book_category);

    //通过作者模糊查询
    @Query("select b from Book b where b.book_author = ?1")
    List<Book> findByBook_author(String book_author);

    //通过书名模糊查询
    @Query(value = "select * from Book b where b.book_name like CONCAT('%', :book_name, '%')", nativeQuery=true)
    List<Book> findByBook_nameLike(@Param("book_name") String book_name);

    //通过书名精确查询
    @Query("select b from Book b where b.book_name = ?1")
    List<Book> findByBook_name(String book_name);

    //通过出版社模糊查询
    @Query("select b from Book b where b.book_publisher = ?1")
    List<Book> findByBook_publiser(String book_publisher);

}
