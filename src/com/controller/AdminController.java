package com.controller;

import com.entity.Admins;
import com.entity.Goods;
import com.entity.Types;
import com.service.*;
import com.util.PageUtil;
import com.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Author linxiaobai
 * @Date 2020/9/18 10:53
 * @Description TODO
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private TopService topService;

    @GetMapping("/login")
    public String login() {
        return "/admin/login.jsp";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpServletRequest request, HttpSession session) {
        Admins admin = adminService.getByUsernameAndPassword(username, password);
        if (Objects.nonNull(admin)) {
            session.setAttribute("admin", admin);
            return "redirect:index";
        }
        request.setAttribute("msg", "用户名或密码错误");
        return "/admin/login.jsp";
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        request.setAttribute("msg", "登入成功");
        return "/admin/index.jsp";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.removeAttribute("admin");
        request.setAttribute("msg", "退出成功@@@@");
        return "/admin/login.jsp";
    }

    @GetMapping("/typeList")
    public String typeList(HttpServletRequest request) {
        request.setAttribute("flag", 1);
        request.setAttribute("typeList", typeService.getList());
        return "/admin/type_list.jsp";
    }

    @GetMapping("/typeAdd")
    public String typeAdd(HttpServletRequest request) {
        request.setAttribute("flag", 1);
        return "/admin/type_add.jsp";
    }

    @PostMapping("/typeSave")
    public String typeSave(Types types) throws Exception {
        typeService.add(types);
        return "redirect:typeList?flag=1";
    }

    @GetMapping("/typeEdit")
    public String typeEdit(int id, HttpServletRequest request) {
        request.setAttribute("flag", 1);
        request.setAttribute("type", typeService.get(id));
        return "/admin/type_edit.jsp";
    }

    @GetMapping("/typeUpdate")
    public String typeUpdate(Types types) {
        typeService.update(types);
        return "redirect:typeList?flag=1";
    }

    @GetMapping("/typeDelete")
    public String typeDelete(int id) {
        typeService.delete(id);
        return "redirect:typeList?flag=1";
    }

    @GetMapping("/goodList")
    public String goodList(HttpServletRequest request,
                           @RequestParam(required = false, defaultValue = "0") byte type,
                           @RequestParam(required = false, defaultValue = "1") int page,
                           @RequestParam(required = false, defaultValue = "10") int size) {
        request.setAttribute("flag", 2);
        request.setAttribute("page", page);
        request.setAttribute("type", type);
        if (type > 0) {
            String goodids = topService.getGoodIdsByType(type);
            request.setAttribute("goodList", goodService.getListByIds(goodids, page, size));
            request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getCountByIds(goodids), page, size));
        } else {
            request.setAttribute("goodList", goodService.getList(page, size));
            request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getCount(), page, size));
        }
        return "/admin/good_list.jsp";
    }

    @GetMapping("/goodAdd")
    public String goodAdd(HttpServletRequest request) {
        request.setAttribute("flag", 2);
        request.setAttribute("typeList", typeService.getList());
        return "/admin/good_add.jsp";
    }

    @PostMapping("/goodSave")
    public String goodSave(Goods goods, MultipartFile file,@RequestParam(required = false,defaultValue = "1")int page)throws Exception{
        goods.setCover(UploadUtil.upload(file));
        goodService.add(goods);
        return "redirect:goodList?flag=2&page="+page;
    }
}
