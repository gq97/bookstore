package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Orders.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

}
