package cn.edu.ncu.bookstore.entity.Orders;


import cn.edu.ncu.bookstore.entity.Book;

import javax.persistence.*;

@Entity
public class Orders_details {

    @EmbeddedId
    OrdersPK ordersPK;

    @MapsId("orders_id")
    @ManyToOne
    @JoinColumn(name = "orders_id", referencedColumnName = "orders_id")
    private Orders orders;

    @MapsId("book_id")
    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    private int book_number;

    //一件商品的价格
    private double book_price;

    public Orders_details(){
    }

    public Orders_details(OrdersPK ordersPK, Orders orders, Book book, int book_number) {
        this.ordersPK = ordersPK;
        this.orders = orders;
        this.book = book;
        this.book_number = book_number;
        this.book_price = book.getBook_realPrice() * book_number;
    }

    public OrdersPK getOrdersPK() {
        return ordersPK;
    }

    public void setOrdersPK(OrdersPK ordersPK) {
        this.ordersPK = ordersPK;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getBook_number() {
        return book_number;
    }

    public void setBook_number(int book_number) {
        this.book_number = book_number;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

}
