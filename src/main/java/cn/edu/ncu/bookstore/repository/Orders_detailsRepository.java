package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Orders.Orders;
import cn.edu.ncu.bookstore.entity.Orders.OrdersPK;
import cn.edu.ncu.bookstore.entity.Orders.Orders_details;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface Orders_detailsRepository extends CrudRepository<Orders_details, OrdersPK> {

    //根据orders_id查询订单内所有商品
    @Query("select o from Orders_details o where o.orders = ?1")
    Set<Orders_details> findOrders_detailsByOrders(Orders orders);


}
