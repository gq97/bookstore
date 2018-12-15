package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Orders.Orders;
import cn.edu.ncu.bookstore.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

    //根据user查询用户所有订单
    @Query("select o from Orders o where o.user= ?1")
    List<Orders> findOrdersByUser(User user);


}
