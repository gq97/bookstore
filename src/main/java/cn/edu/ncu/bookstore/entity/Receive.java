package cn.edu.ncu.bookstore.entity;


import javax.persistence.*;

@Entity
public class Receive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rec_id;

    private String rec_addr;

    private String rec_phone;

    private String rec_name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Receive(){

    }

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public String getRec_addr() {
        return rec_addr;
    }

    public void setRec_addr(String rec_addr) {
        this.rec_addr = rec_addr;
    }

    public String getRec_phone() {
        return rec_phone;
    }

    public void setRec_phone(String rec_phone) {
        this.rec_phone = rec_phone;
    }

    public String getRec_name() {
        return rec_name;
    }

    public void setRec_name(String rec_name) {
        this.rec_name = rec_name;
    }


}
