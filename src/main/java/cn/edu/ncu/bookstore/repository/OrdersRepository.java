package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Orders.Orders;
import cn.edu.ncu.bookstore.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

    //查询用户所有订单，根据订单创建时间降序排序
    @Query("select o from Orders o where o.user= ?1 order by o.orders_time desc")
    List<Orders> findOrdersByUser(User user);


}
