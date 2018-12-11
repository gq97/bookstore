package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Cart;
import cn.edu.ncu.bookstore.entity.CartKey;
import cn.edu.ncu.bookstore.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, CartKey> {

    //通过用户ID查询购物车
   // @Query(value = "select c.* from Cart c JOIN User u ON c.user = u where c.user_id=?1")
    List<Cart> findCartByUser(User user);

}
