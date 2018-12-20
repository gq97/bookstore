package cn.edu.ncu.bookstore.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private String comment_text;

    private Timestamp comment_time;

    //评分等级
    private int comment_star;

    //评论图url
    private String comment_image;

    public Comment(){}

    public Comment(Book book, User user, String comment_text, Timestamp comment_time, int comment_star, String comment_image) {
        this.book = book;
        this.user = user;
        this.comment_text = comment_text;
        this.comment_time = comment_time;
        this.comment_star = comment_star;
        this.comment_image = comment_image;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Timestamp getComment_time() {
        return comment_time;
    }

    public void setComment_time(Timestamp comment_time) {
        this.comment_time = comment_time;
    }

    public int getComment_star() {
        return comment_star;
    }

    public void setComment_star(int comment_star) {
        this.comment_star = comment_star;
    }

    public String getComment_image() {
        return comment_image;
    }

    public void setComment_image(String comment_image) {
        this.comment_image = comment_image;
    }
}
