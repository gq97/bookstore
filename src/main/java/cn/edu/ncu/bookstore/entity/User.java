package cn.edu.ncu.bookstore.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
public class User {

    @Id //主键
    private  String user_id;

    @Size(min = 6, message = "Your password must have at least 6 characters and at most 14 characters")
    private  String user_password;

    @Email(message = "Please provide a valid Email!")
    @NotEmpty(message = "Email should not be empty!")
    private String email;

    private String sex;

    private String phone;

    @OneToMany(mappedBy = "user")
    private Set<Receive> receive;

    public User() {//无参默认构造器
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Receive> getReceive() {
        return receive;
    }

    public void setReceive(Set<Receive> receive) {
        this.receive = receive;
    }
}

