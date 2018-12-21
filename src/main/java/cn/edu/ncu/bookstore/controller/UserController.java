package cn.edu.ncu.bookstore.controller;

import cn.edu.ncu.bookstore.config.MyUserDetails;
import cn.edu.ncu.bookstore.entity.Cart;
import cn.edu.ncu.bookstore.entity.User;
import cn.edu.ncu.bookstore.repository.CartRepository;
import cn.edu.ncu.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller

public class UserController {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    private static PasswordEncoder passwordEncoder;

    static {
        passwordEncoder = new BCryptPasswordEncoder(4);
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping("login-error")
    public String login_error(Model model){
        model.addAttribute("login_error", "login_error");
        return "redirect:/login";
    }

    @RequestMapping(value = "/login-success")
    public String login_success(Model model){
        User user = getUser();
        String username = user.getUser_id();
        model.addAttribute("user", user);
        model.addAttribute("username", username);
        return "redirect:/index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, String username, String email, String password){
        System.out.println("Register...");
        if(userRepository.findById(username).isPresent() || userRepository.findByEmail(email).size() != 0){
           return "redirect:json/false.json";
        }
        User user = new User() ;
        user.setUser_id(username);
        user.setEmail(email);
        user.setUser_password(passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:json/true.json";
    }

    //更新密码
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(Model model, String user_id, String oldPassword, String newPassword){
        System.out.println("updateUser...");
        if(!userRepository.findById(user_id).isPresent()){
            return "redirect:json/false.json";
        }
        User user = getUser();
        String password = user.getUser_password();
        if(!passwordEncoder.matches(oldPassword, password)){
            System.out.println("Username is " + user.getUser_id());
            System.out.println("Password is " + password);
            System.out.println("Your input password is " + oldPassword);
            System.out.println("Your input password is " + passwordEncoder.encode(oldPassword));
            return "redirect:json/false.json";
        }
        user.setUser_password(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "redirect:json/true.json";
    }

    //获取当前User
    public User getUser(){
        MyUserDetails myUserDetails = (MyUserDetails)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return userRepository.findById(myUserDetails.getUsername()).get();
    }



    /*
    //查词所有用户
    @GetMapping("/userlist")
    public ModelAndView userList(Model model){
        model.addAttribute("userList",userRepository.findAll());
        model.addAttribute("title","用户管理");
        return  new ModelAndView("user/list","userModel",model);
    }
    //根据id 查询用户
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        Optional<User> user= userRepository.findById(id);
        model.addAttribute("user",user.get());
        model.addAttribute("title","查看用户");
        return new ModelAndView("user/view" ,"userModel",model);
    }

    //获取创建表单页面
    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        model.addAttribute("title","创建用户");
        return new ModelAndView("user/form","userModel",model);
    }

    //保存用户
    @PostMapping
    public ModelAndView saveOrUpdateUser(User user){
        user =userRepository.save(user);
        return new ModelAndView("redirect:/user/userlist");
    }

    //根据id删除用户
    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return new ModelAndView("redirect:/user/userlist");
    }

    //修改用户界面
    @GetMapping(value = "edit/{id}")
    public ModelAndView editForm(@PathVariable("id") Long id,Model model){
        Optional<User> user =userRepository.findById(id);
        model.addAttribute("user",user);
        model.addAttribute("title","编辑用户");
        return new ModelAndView("user/form" ,"userModel",model);
    }*/
}

