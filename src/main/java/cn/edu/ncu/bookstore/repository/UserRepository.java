package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByEmail(String user_email);

    @Query(value = "select user_password from user where email = ?", nativeQuery = true)
    String getUserPaswordByEmail(String user_email);

    @Query(value = "select user_password from user where user_id = ?", nativeQuery = true)
    String getUserPasswordByUser_id(String user_id);

    //User findByUsername(String user_id);
    /*
    User saveOrUpdateUser(User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> userList();*/
}
