package cn.edu.ncu.bookstore.entity.Orders;

import cn.edu.ncu.bookstore.entity.Receive;
import cn.edu.ncu.bookstore.entity.User;

import javax.persistence.*;

@Entity
public class Orders {


    @Id //主键
    @GeneratedValue(strategy = GenerationType.AUTO) //设置自增
    private int orders_id;

    @OneToOne
    @JoinColumn(name = "rec_id", referencedColumnName = "rec_id")
    private Receive receive;

    private double orders_money;

    private int orders_status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;


    public Orders(){

    }
    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public Receive getReceive() {
        return receive;
    }

    public void setReceive(Receive receive) {
        this.receive = receive;
    }

    public double getOrders_money() {
        return orders_money;
    }

    public void setOrders_money(double orders_money) {
        this.orders_money = orders_money;
    }

    public int getOrders_status() {
        return orders_status;
    }

    public void setOrders_status(int orders_status) {
        this.orders_status = orders_status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
