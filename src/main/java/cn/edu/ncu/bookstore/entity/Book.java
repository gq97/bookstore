package cn.edu.ncu.bookstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Book  {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置自增
    private int book_id;
    private String book_name;
    private String book_author;
    private String book_publisher;
    private Timestamp book_publishDate;
    private String book_introduce;
    private String book_category;
    private double book_price; //定价
    private double book_realPrice; //折扣价，保留两位小数
    private int book_amount;
    private String book_url;
    private String book_ISBN;

    public String getBook_ISBN() {
        return book_ISBN;
    }

    public void setBook_ISBN(String book_ISBN) {
        this.book_ISBN = book_ISBN;
    }

    public double getBook_discount() {
        return book_discount;
    }

    public void setBook_discount(double book_discount) {
        this.book_discount = book_discount;
    }

    public String getBook_content() {
        return book_content;
    }

    public void setBook_content(String book_content) {
        this.book_content = book_content;
    }

    private double book_discount;
    private String book_content;


    public Book(){
        this.book_realPrice = toDecimalFormat(this.book_price*this.book_discount*0.1);
    }
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public Timestamp getBook_publishDate() {
        return book_publishDate;
    }

    public void setBook_publishDate(Timestamp book_publishDate) {
        this.book_publishDate = book_publishDate;
    }

    public String getBook_introduce() {
        return book_introduce;
    }

    public void setBook_introduce(String book_introduce) {
        this.book_introduce = book_introduce;
    }

    public String getBook_category() {
        return book_category;
    }

    public void setBook_category(String book_category) {
        this.book_category = book_category;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    public int getBook_amount() {
        return book_amount;
    }

    public void setBook_amount(int book_amount) {
        this.book_amount = book_amount;
    }

    public String getBook_url() {
        return book_url;
    }

    public void setBook_url(String book_url) {
        this.book_url = book_url;
    }


    //四舍五入保留两位小数
    public double toDecimalFormat(double number) {
        return (int)(number * 100 + 0.5)*0.01;
    }

    public double getBook_realPrice() {
        return book_realPrice;
    }

    public void setBook_realPrice(double book_realPrice) {
        this.book_realPrice = book_realPrice;
    }

}
