package cn.edu.ncu.bookstore.entity.Orders;


import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class OrdersPK implements Serializable {

    private static final long serialVersionUID = -3010387339173861972L;

    int book_id;

    int orders_id;


    public OrdersPK(){
    }

    public OrdersPK(int book_id, int orders_id) {
        super();
        this.book_id = book_id;
        this.orders_id = orders_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + orders_id;
        result = prime * result + book_id;
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        OrdersPK other = (OrdersPK) obj;
        if(orders_id != other.orders_id){
            return false;
        } else if(book_id != other.book_id){
            return false;
        }
        return true;
    }

}
