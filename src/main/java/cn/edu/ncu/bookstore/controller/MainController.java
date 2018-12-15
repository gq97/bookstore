package cn.edu.ncu.bookstore.controller;

import cn.edu.ncu.bookstore.config.MyUserDetails;
import cn.edu.ncu.bookstore.entity.*;
import cn.edu.ncu.bookstore.entity.Orders.Orders;
import cn.edu.ncu.bookstore.entity.Orders.OrdersPK;
import cn.edu.ncu.bookstore.entity.Orders.Orders_details;
import cn.edu.ncu.bookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ReceiveRepository receiveRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private Orders_detailsRepository orders_detailsRepository;

    public boolean isExistUser(){
        MyUserDetails myUserDetails = (MyUserDetails)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return myUserDetails.getUsername() == null ? false : true;
    }

    //获取当前User
    public User getUser(){
        MyUserDetails myUserDetails = (MyUserDetails)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return userRepository.findById(myUserDetails.getUsername()).get();
    }

    //查词所有分类
    //@ModelAttribute 注释的方法每次调用该控制器类的请求处理方法时都会被调用 可添加全局变量
    @ModelAttribute
    public void selectCategory(Model model){
        model.addAttribute("categoryList", categoryRepository.findAll());
    }

    //记住用户登录状态
    @ModelAttribute
    public void addUser(Model model) {
        //判断用户是否已登录
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)){
            User user = getUser();
            String username = user.getUser_id();
            model.addAttribute("user", user);
            model.addAttribute("username", username);
        }
    }

    @GetMapping("/")
    public String root(){
        return "redirect:/index";
    }


    @GetMapping("/index")
    public String index(Model model){
        //model.addAttribute("categoryList", categoryRepository.findAll());
        return  "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登录失败，用户名或密码错误");
        return "login";
    }

    private Timestamp getCurrentTime() {
        return new Timestamp(new Date().getTime());
    }

    //添加到购物车
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public String addToCart(Model model, Integer book_id, Integer book_amount) {
        User user = getUser();
        String user_id = user.getUser_id();
        Book book = bookRepository.findById(book_id).get();
        CartKey cart_id = new CartKey(user_id, book_id);
        Cart cart;
        if(cartRepository.findById(cart_id).isPresent()) {
            //取出之前购物车的同类商品
            cart = cartRepository.findById(cart_id).get();
            cart.setBook_amount(cart.getBook_amount() + book_amount);
            cart.setUpdate_time(getCurrentTime());
        } else {
            cart = new Cart(user_id, user, book_id, book,  book_amount, 1, getCurrentTime(), getCurrentTime());
        }
        cartRepository.save(cart);
        return "redirect:json/true.json";
    }


    //查询所有图书
    @RequestMapping("/listAll")
    public String listAll(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "listBooks";
    }

    //查询分类图书
    @RequestMapping("/listBooks")
    public String listBooks(Model model, String categoryName){
        List<Book> books = bookRepository.findByBook_category(categoryName);
        model.addAttribute("books", books);
        return "listBooks";
    }

    //根据id查询图书
    @RequestMapping("/bookDetails")
    public String listBook(Model model, Integer book_id){
        Book book = bookRepository.findById(book_id).get();
        model.addAttribute("book", book);
        return "bookDetails";
    }

    //搜索框
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, Integer source, String keyword) {
        String[] sourceC = {"书名", "作者", "出版社"};
        List<Book> books;
        if(source == 0) {
            books = bookRepository.findByBook_nameLike(keyword);
        } else {
            books = source == 1 ? bookRepository.findByBook_author(keyword) : bookRepository.findByBook_publiser(keyword);
        }
        for(Book book : books) {
            System.out.println(book.getBook_name());
        }
        model.addAttribute("books", books);
        model.addAttribute("sourceC", sourceC[source]);
        model.addAttribute("keyword", keyword);
        model.addAttribute("books_num", books.size());
        return "search";
    }

    //查看购物车
    @RequestMapping(value = "/cart")
    public String cart(Model model) {
        User user = getUser();
        List<Cart> carts = cartRepository.findCartByUser(user);
        model.addAttribute("carts", carts);
        return "cart";
    }

    //四舍五入保留两位小数(扩大了100倍）
    public int toDecimalFormat(double number) {
        return (int)(number * 100 + 0.5);
    }

    //计算所勾选中的总金额
    public double cal_totalPrice() {
        int sum = 0;
        User user = getUser();
        List<Cart> carts = cartRepository.findCartByUser(user);
        for(Cart cart : carts) {
            if(cart.getCart_status() == 1) {
                int price = toDecimalFormat(cart.getBook().getBook_price()*cart.getBook().getBook_discount()*0.1);
                sum += price * cart.getBook_amount();
            }
        }
        return sum / 100.0;
    }

    //删除购物车中的某件商品
    @RequestMapping(value = "/deleteFromCart")
    public String deleteFromCart(Model model, Integer book_id) {
        User user = getUser();
        CartKey cartKey =  new CartKey(user.getUser_id(), book_id);
        cartRepository.deleteById(cartKey);
        model.addAttribute("totalPrice", cal_totalPrice());
        return "redirect:json/true.json";
    }

    //更新购物车
    @RequestMapping(value = "/updateCart")
    public String updateCart(Model model, Integer book_id, Integer book_amount) {
        User user = getUser();
        CartKey cartKey = new CartKey(user.getUser_id(), book_id);
        Cart cart = cartRepository.findById(cartKey).get();
        cart.setBook_amount(book_amount);
        cart.setUpdate_time(getCurrentTime());
        cartRepository.save(cart);
        model.addAttribute("totalPrice", cal_totalPrice());
        return "redirect:json/true.json";
    }

    //更新购物车中商品的状态
    @RequestMapping(value = "/updateCart_status")
    public String updateCart_status(Model model, Integer[] cart_status) {
        User user = getUser();
        List<Cart> carts = cartRepository.findCartByUser(user);
        int i = 0;
        for(Cart cart : carts) {
            cart.setCart_status(cart_status[i]);
            cartRepository.save(cart);
            i++;
        }
        model.addAttribute("totalPrice", cal_totalPrice());
        return "redirect:json/true.json";
    }

    //确认收货信息
    @RequestMapping("/confirmOrder")
    public String confirmOrder(Model model) {
        String user_id  = getUser().getUser_id();
        List<Receive> receives = receiveRepository.findReceiveByUser_id(user_id);
        model.addAttribute("receives", receives);
        int i = 0, receive_default = -1;
        for(Receive receive : receives) {
            if(receive.getReceive_isDefault() == true) {
                receive_default = i;
            }
            i++;
        }
        model.addAttribute("receive_default", receive_default);
        model.addAttribute("totalPrice", cal_totalPrice());
        return "confirmOrder";
    }

    //新增地址
    @RequestMapping(value = "/addReceive")
    public String addReceive(Model model, String receive_name,String receive_address, String receive_street, String receive_phone) {
        System.out.println("receive_name "+receive_name);
        User user = getUser();
        Receive receive = new Receive(receive_address, receive_street, receive_phone, receive_name, false, user);
        receiveRepository.save(receive);
        model.addAttribute("totalPrice", cal_totalPrice());
        return "redirect: json/true.json";
    }

    //更新地址
    @RequestMapping(value = "/updateReceive")
    public String updateReceive(Model model, Integer receive_id, String receive_name,String receive_address, String receive_street, String receive_phone) {
        User user = getUser();
        Receive receive = receiveRepository.findById(receive_id).get();
        receive.setReceive_name(receive_name);
        receive.setReceive_address(receive_address);
        receive.setReceive_street(receive_street);
        receive.setReceive_phone(receive_phone);
        receiveRepository.save(receive);
        model.addAttribute("totalPrice", cal_totalPrice());
        return "redirect: json/true.json";
    }

    //更新默认地址
    @RequestMapping(value = "/updateReceive_default")
    public String updateReceive_default(Model model, Integer receive_id, String receive_name,String receive_address, String receive_street, String receive_phone) {
        User user = getUser();
        int result = receiveRepository.setDefaultReceiveByUser(user, false);
        Receive receive = receiveRepository.findById(receive_id).get();
        receive.setReceive_isDefault(true);
        receiveRepository.save(receive);
        model.addAttribute("totalPrice", cal_totalPrice());
        return "redirect: json/true.json";
    }

    //成功提交订单
    @RequestMapping(value = "/submitOrder")
    public String submitOrder(Model model, Integer receive_id) {
        User user = getUser();
        Receive receive = receiveRepository.findById(receive_id).get();
        double orders_money = cal_totalPrice();
        int orders_status = 1;
        Timestamp orders_time = getCurrentTime();
        Orders orders = new Orders(receive, orders_money, orders_status, orders_time, user);
        ordersRepository.save(orders);
        List<Cart> carts = cartRepository.findCartByUser(user);
        int orders_id = orders.getOrders_id();
        for(Cart cart : carts) {
            if(cart.getCart_status() == 1) {
                int book_id = cart.getBook().getBook_id();
                Book book = cart.getBook();
                int book_number = cart.getBook_amount();
                //减少库存
                bookRepository.updateBook_amount(book_id, -book_number);
                //添加到订单详情
                OrdersPK ordersPK = new OrdersPK(book_id, orders_id);
                Orders_details orders_details = new Orders_details(ordersPK, orders, book, book_number);
                orders_detailsRepository.save(orders_details);
            }
        }
        //删除选中的购物车
        cartRepository.deleteCartByUser(user, 1);
        return "submitOrder";
    }


    @RequestMapping("/order")
    public String order(Model model) {
        User user = getUser();
        List<Orders> allOrders = ordersRepository.findOrdersByUser(user);
        List<Orders> unfilledOrders = new ArrayList<Orders>();
        List<Orders> pendingOrders = new ArrayList<Orders>();
        List<Orders> receivedOrders = new ArrayList<Orders>();
        for(int i = 0; i<allOrders.size(); i++) {
            allOrders.get(i).setOrders_details(orders_detailsRepository.findOrders_detailsByOrders(allOrders.get(i)));
            switch (allOrders.get(i).getOrders_status()) {
                case 1: {
                    unfilledOrders.add(allOrders.get(i));
                    break;
                }
                case 2: {
                    pendingOrders.add(allOrders.get(i));
                    break;
                }
                case 3: {
                    receivedOrders.add(allOrders.get(i));
                    break;
                }
                default: System.out.println("Error");
            }
        }
        model.addAttribute("allOrders", allOrders);
        model.addAttribute("unfilledOrders", unfilledOrders);
        model.addAttribute("pendingOrders", pendingOrders);
        model.addAttribute("receivedOrders", receivedOrders);

        return "order";
    }

}
