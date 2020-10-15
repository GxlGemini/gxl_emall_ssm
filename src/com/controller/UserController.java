package com.controller;

import com.config.ExceptionConfig;
import com.entity.Orders;
import com.entity.Users;
import com.service.CartService;
import com.service.GoodService;
import com.service.OrderService;
import com.service.UserService;
import com.util.PageUtil;
import com.util.SafeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Author linxiaobai
 * @Date 2020/9/18 10:54
 * @Description TODO
 * @Version 1.0
 **/
@Controller
@RequestMapping("index")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private CartService cartService;

    @Resource
    private OrderService orderService;

    @Resource
    private GoodService goodService;

    @GetMapping("/register")
    public String reg() {
        return "/index/register.jsp";
    }

    @PostMapping("/register")
    public String register(Users users, HttpServletRequest request) {
        if (users.getUsername().isEmpty()) {
            request.setAttribute("msg", "用户名不能为空！");
        } else if (Objects.nonNull(userService.getByUsername(users.getUsername()))) {
            request.setAttribute("msg", "用户名已存在！");
        } else {
            userService.add(users);
            request.setAttribute("msg", "注册成功，可以去登入");
            return "/index/login.jsp";
        }
        return "/index/register.jsp";

    }

    @GetMapping("/login")
    public String login() {


        return "/index/login.jsp";
    }

    @PostMapping("/login")
    public String login(Users users, HttpServletRequest request, HttpSession session) {
        Users loginUser = userService.getByUsernameAndPassword(users.getUsername(), users.getPassword());
        if (Objects.isNull(loginUser)) {
            request.setAttribute("msg", "用户名或密码错误");
            return "/index/login.jsp";
        }
        session.setAttribute("user", loginUser);
        session.setAttribute("cartCount", cartService.getCount(loginUser.getId()));
        String referer = request.getHeader("referer");
        System.out.println(referer);
        return "redirect:index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("cartCount");
        return "/index/login.jsp";
    }

    /**
     * 密码
     *
     * @return
     */
    @GetMapping("/password")
    public String password() {
        return "/index/password.jsp";
    }

    @PostMapping("/passwordUpdate")
    public String passwordUpdate(String password, String passwordNew, HttpServletRequest request, HttpSession session) {
        Users users = (Users) session.getAttribute("user");
        users = userService.get(users.getId());
        if (!users.getPassword().equals(SafeUtil.encode(password))) {
            request.setAttribute("msg", "原密码错误2333");
        } else {
            userService.updatePassword(users.getId(), passwordNew);
            request.setAttribute("msg", "密码修改成功");
        }
        return "/index/password.jsp";
    }

    /**
     * 收货地址
     *
     * @return
     */
    @GetMapping("/address")
    public String address() {
        return "/index/address.jsp";
    }

    @PostMapping("/addressUpdate")
    public String addressUpdate(String name, String phone, String address, HttpServletRequest request, HttpSession session) {
        Users users = (Users) session.getAttribute("user");
        userService.update(users.getId(), name, phone, address);
        session.setAttribute("user", userService.get(users.getId()));
        request.setAttribute("msg", "信息修改成功");
        return "/index/address.jsp";
    }


    @GetMapping("/cartTotal")
    public @ResponseBody
    int cartTotal(HttpSession session) {
        Users users = (Users) session.getAttribute("user");
        return cartService.getTotal(users.getId());
    }

    @GetMapping("/orderSave")
    public String orderSave(ServletRequest request, HttpSession session) throws ExceptionConfig.MyException {
        Users users = (Users) session.getAttribute("user");
        int orderId = orderService.save(users.getId());
        session.removeAttribute("cartCount");
        return "redirect:orderPay?id=" + orderId;
    }

    @GetMapping("/orderPay")
    public String orderPay(int id, ServletRequest request) {
        request.setAttribute("order", orderService.get(id));
        return "/index/pay.jsp";
    }

    @PostMapping("/orderPay")
    public String orderPay(Orders orders){
        orderService.pay(orders);
        return "/index/payok.jsp";
    }

    /**
     * @return
     */
    @GetMapping("/order")
    public String order(HttpServletRequest request,HttpSession session,
                        @RequestParam(required = false,defaultValue = "1")int page,
                        @RequestParam(required = false,defaultValue = "6")int size)
                         {
                             Users users= (Users) session.getAttribute("user");
                             request.setAttribute("orderList", orderService.getListByUserid(users.getId(), page, size));
                             request.setAttribute("pageHtml", PageUtil.getPageHtml(request, orderService.getCountByUserid(users.getId()), page, size));
        return "/index/order.jsp";
    }

    @GetMapping("/cart")
    public String cart(HttpServletRequest request, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        request.setAttribute("cartList", cartService.getList(user.getId()));
        request.setAttribute("cartCount", cartService.getCount(user.getId()));
        request.setAttribute("cartTotal", cartService.getTotal(user.getId()));
        return "/index/cart.jsp";
    }

    @PostMapping("/cartBuy")
    public @ResponseBody
    boolean cartBuy(int goodId, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        return cartService.save(goodId, user.getId());
    }

    @PostMapping("/cartAdd")
    public @ResponseBody
    boolean cartAdd(int id) {
        return cartService.add(id);
    }

    @PostMapping("/cartLess")
    public @ResponseBody
    boolean cartLess(int id) {
        return cartService.less(id);
    }

    @PostMapping("/cartDelete")
    public @ResponseBody
    boolean cartDelete(int id) {
        return cartService.delete(id);
    }
}
