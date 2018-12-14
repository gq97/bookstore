package cn.edu.ncu.bookstore.entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
public class Cart {

    //user_id和book_id做复合主键
    @EmbeddedId
    private CartKey cart_id;

    @MapsId("user_id")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @MapsId("book_id")
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    //购物车中某商品的状态， 1为正在结算，0为未勾选
    private Integer cart_status;

    private Integer book_amount;

    private Timestamp add_time;

    private Timestamp update_time;

    public Cart(){

    }

    public Cart(String user_id, User user, Integer book_id, Book book,  Integer book_amount, Integer cart_status, Timestamp add_time, Timestamp update_time) {
        this.cart_id = new CartKey(user_id, book_id);
        this.user = user;
        this.book = book;
        this.book_amount = book_amount;
        this.cart_status = cart_status;
        this.add_time = add_time;
        this.update_time = update_time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CartKey getCart_id() {
        return cart_id;
    }

    public void setCart_id(CartKey cart_id) {
        this.cart_id = cart_id;
    }

    public Integer getBook_amount() {
        return book_amount;
    }

    public void setBook_amount(Integer book_amount) {
        this.book_amount = book_amount;
    }

    public Integer getCart_status() {
        return cart_status;
    }

    public void setCart_status(Integer cart_status) {
        this.cart_status = cart_status;
    }

    public Timestamp getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Timestamp add_time) {
        this.add_time = add_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

}
