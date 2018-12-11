package cn.edu.ncu.bookstore.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartKey implements Serializable {

    private String user_id;

    private int book_id;

    private int cart_status;

    public CartKey(){}

    public CartKey(String user_id, int book_id, int cart_status) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.cart_status = cart_status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getCart_status() {
        return cart_status;
    }

    public void setCart_status(int cart_status) {
        this.cart_status = cart_status;
    }

    @Override
    public String toString() {
        return "CartKey [user_id=" + user_id + ", book_id=" + book_id;
    }

}
