package com.controller;

import com.entity.Goods;
import com.entity.Tops;
import com.entity.Types;
import com.service.GoodService;
import com.service.OrderService;
import com.service.TypeService;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author linxiaobai
 * @Date 2020/9/18 10:54
 * @Description TODO 前台相关接口
 * @Version 1.0
 **/
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TypeService typeService;


    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        request.setAttribute("flag", 1);
        List<Goods> todayList = goodService.getListByTopType(Tops.TYPE_TODAY, 1, 10);
        request.setAttribute("todayList", todayList);
        List<Goods> hotList = goodService.getListOrderSales(1, 10);
        request.setAttribute("hotList", hotList);
        List<Types> typesList = typeService.getList();
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Types types : typesList) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", types);
            map.put("goodList", goodService.getListByType(types.getId(), 1, 15));
            dataList.add(map);
        }
        request.setAttribute("dataList", dataList);
        return "/index/index.jsp";
    }

    @GetMapping("/type")
    public String type(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "0") int id,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int size) {
        request.setAttribute("type", typeService.get(id));
        request.setAttribute("goodList", goodService.getListByType(id, page, size));
        request.setAttribute("pageHtml", PageUtil.getPageHtml(request, goodService.getCountByType(id), page, size));
        return "/index/goods.jsp";
    }

    @GetMapping("/today")
    public String today(HttpServletRequest request,
                        @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int size) {
        request.setAttribute("flag", 2);
        request.setAttribute("goodList", goodService.getListByTopType(Tops.TYPE_TODAY, page, size));
        request.setAttribute("pageHtml", PageUtil.getPageHtml(request, goodService.getCountByTopType(Tops.TYPE_TODAY), page, size));
        return "/index/goods.jsp";
    }

    @GetMapping("/hot")
    public String host(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int size) {
        request.setAttribute("flag", 3);
        request.setAttribute("goodList", goodService.getListOrderSales(page, size));
        request.setAttribute("pageHtml", PageUtil.getPageHtml(request, goodService.getCount(), page, size));
        return "/index/goods.jsp";
    }

    @GetMapping("/new")
    public String newG(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int size) {
        request.setAttribute("flag", 4);
        request.setAttribute("goodList", goodService.getList(page, size));
        request.setAttribute("pageHtml", PageUtil.getPageHtml(request, goodService.getCount(), page, size));
        return "/index/goods.jsp";
    }
    @GetMapping("/detail")
    public String detail(int id, HttpServletRequest request) {
        request.setAttribute("good", goodService.get(id));
        request.setAttribute("todayList", goodService.getListByTopType(Tops.TYPE_TODAY, 2, 2));
        return "/index/detail.jsp";
    }

}
