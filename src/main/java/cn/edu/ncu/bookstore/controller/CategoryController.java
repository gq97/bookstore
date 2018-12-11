package cn.edu.ncu.bookstore.controller;


import cn.edu.ncu.bookstore.entity.Category;
import cn.edu.ncu.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    //查词所有分类
    @GetMapping("/categorylist")
    public ModelAndView userList(Model model){
        model.addAttribute("categoryList", categoryRepository.findAll());
        return  new ModelAndView("category/list","categoryModel", model);
    }

    //根据id 查询分类
    @GetMapping("{id}")
    public List<Category> view(@PathVariable("id") Integer id){
        List<Category> category= (List<Category>) categoryRepository.findAll();
        return category;
    }
}
