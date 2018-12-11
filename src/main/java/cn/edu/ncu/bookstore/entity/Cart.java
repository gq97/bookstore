package cn.edu.ncu.bookstore.entity;

import javax.persistence.*;
import java.util.Set;

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

    private int book_amount;

    public Cart(){

    }

    public Cart( String user_id, User user, Integer book_id, Book book, Integer cart_status, int book_amount) {
        this.cart_id = new CartKey(user_id, book_id, cart_status);
        this.user = user;
        this.book = book;
        this.book_amount = book_amount;
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

    public int getBook_amount() {
        return book_amount;
    }

    public void setBook_amount(int book_amount) {
        this.book_amount = book_amount;
    }

}
