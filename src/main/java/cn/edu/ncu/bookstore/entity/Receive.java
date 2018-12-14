package cn.edu.ncu.bookstore.entity;



import cn.edu.ncu.bookstore.entity.Orders.Orders;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Receive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receive_id;

    private String receive_address;

    private String receive_street;

    private String receive_phone;

    private String receive_name;

    private Boolean receive_isDefault;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    //一条收货信息对应多个订单
    @OneToMany(mappedBy = "receive", cascade = CascadeType.ALL)
    private Set<Orders> orders;

    public Receive(){}

    public Receive(String receive_address, String receive_street, String receive_phone, String receive_name, Boolean receive_isDefault, User user) {
        this.receive_address = receive_address;
        this.receive_street = receive_street;
        this.receive_phone = receive_phone;
        this.receive_name = receive_name;
        this.receive_isDefault = receive_isDefault;
        this.user = user;
    }

    public int getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(int receive_id) {
        this.receive_id = receive_id;
    }

    public String getReceive_address() {
        return receive_address;
    }

    public void setReceive_address(String receive_address) {
        this.receive_address = receive_address;
    }

    public String getReceive_street() {
        return receive_street;
    }

    public void setReceive_street(String receive_street) {
        this.receive_street = receive_street;
    }

    public String getReceive_phone() {
        return receive_phone;
    }

    public void setReceive_phone(String receive_phone) {
        this.receive_phone = receive_phone;
    }

    public String getReceive_name() {
        return receive_name;
    }

    public void setReceive_name(String receive_name) {
        this.receive_name = receive_name;
    }

    public Boolean getReceive_isDefault() {
        return receive_isDefault;
    }

    public void setReceive_isDefault(Boolean receive_isDefault) {
        this.receive_isDefault = receive_isDefault;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
