package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Receive;
import cn.edu.ncu.bookstore.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReceiveRepository extends CrudRepository<Receive, Integer> {

    //根据用户名查询所有地址
    @Query("select r from Receive r where user_id=?1")
    List<Receive> findReceiveByUser_id(String user_id);

    //修改用户的默认地址
    @Transactional //更新或者删除时需要加上事务@Transactional
    @Modifying //@Query无法进行DML，需要加上@Modifying
    @Query("update Receive r set r.receive_isDefault=?2 where r.user=?1")
    int setDefaultReceiveByUser(User user, Boolean receive_isDefault);

}
