package cn.edu.ncu.bookstore.entity.Orders;

import cn.edu.ncu.bookstore.entity.Receive;
import cn.edu.ncu.bookstore.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Orders {


    @Id //主键
    @GeneratedValue(strategy = GenerationType.AUTO) //设置自增
    private int orders_id;

    @ManyToOne
    @JoinColumn(name = "receive_id", referencedColumnName = "receive_id")
    private Receive receive;

    private double orders_money;

    //订单状态 1-待发货 2-待收货 3-待评价 4-已评价
    private int orders_status;

    //订单创建时间
    private Timestamp orders_time;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    //一个订单对应多个订单详情商品
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<Orders_details> orders_details;


    public Orders(){

    }

    public Orders(Receive receive, double orders_money, int orders_status, Timestamp orders_time, User user) {
        this.receive = receive;
        this.orders_money = orders_money;
        this.orders_status = orders_status;
        this.orders_time = orders_time;
        this.user = user;
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

    public Timestamp getOrders_time() {
        return orders_time;
    }

    public void setOrders_time(Timestamp orders_time) {
        this.orders_time = orders_time;
    }

    public Set<Orders_details> getOrders_details() {
        return orders_details;
    }

    public void setOrders_details(Set<Orders_details> orders_details) {
        this.orders_details = orders_details;
    }
}
