package cn.edu.ncu.bookstore.repository;

import cn.edu.ncu.bookstore.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
