package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Orders.OrdersPK;
import cn.edu.ncu.bookstore.entity.Orders.Orders_details;
import org.springframework.data.repository.CrudRepository;

public interface Orders_detailsRepository extends CrudRepository<Orders_details, OrdersPK> {

}
