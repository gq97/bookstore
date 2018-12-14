package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Cart;
import cn.edu.ncu.bookstore.entity.CartKey;
import cn.edu.ncu.bookstore.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, CartKey> {

    //通过用户ID查询购物车内未结算的商品
   // @Query(value = "select c.* from Cart c JOIN User u ON c.user = u where c.user_id=?1")
    List<Cart> findCartByUser(User user);

    //通过用户ID查询购物车内未结算的商品
    @Query(value = "select c from Cart c where c.user=?1")
    List<Cart> findCartByUser_id(User user);

    //通过用户删除已结算的商品
    @Transactional
    @Modifying
    @Query("delete from Cart c where c.user=?1 and c.cart_status=?2")
    int deleteCartByUser(User user, Integer cart_status);


}
