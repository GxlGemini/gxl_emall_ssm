package com.service;

import com.config.ExceptionConfig;
import com.dao.ItemDao;
import com.dao.OrderDao;
import com.entity.Carts;
import com.entity.Items;
import com.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 15:13
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class OrderService {

    @Autowired
    private CartService cartService;
    @Autowired
    private GoodService goodService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private UserService userService;

    @Transactional
    public int save(int userId) throws ExceptionConfig.MyException {
        List<Carts> cartsList = cartService.getList(userId);
        if (Objects.isNull(cartsList) || cartsList.isEmpty()) {
            throw new ExceptionConfig.MyException("购物车没有商品");
        }
        for (Carts carts : cartsList) {
            if (carts.getGood().getStock() < carts.getAmount()) {
                throw new ExceptionConfig.MyException("商品 [ " + carts.getGood().getName() + " ] 库存不足 ");
            }
            goodService.updateStock(carts.getGood().getId(), carts.getAmount());
            goodService.updateSales(carts.getGood().getId(), carts.getAmount());
        }
        int total = 0;
        for (Carts carts : cartsList) {
            total += carts.getGood().getPrice() * carts.getAmount();
        }
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setTotal(total);
        orders.setAmount(cartsList.size());
        orders.setStatus(Orders.STATUS_UNPAY);
        orders.setSystime(new Date());
        orderDao.insert(orders);
        int ordersId = orders.getId();
        for (Carts carts : cartsList) {
            Items item=new Items();
            item.setOrderId(ordersId);
            item.setGoodId(carts.getGoodId());
            item.setPrice(carts.getGood().getPrice());
            item.setAmount(carts.getAmount());
            itemDao.inset(item);
        }
        cartService.clean(userId);
        return ordersId;
    }

    private List<Orders> pack(List<Orders> list) {
        if (Objects.nonNull(list) && !list.isEmpty()) {
            for (Orders orders : list) {
                orders = pack(orders);
            }
        }
            return list;
    }


    public Orders get(int id) {
        return pack(orderDao.select(id));
    }
    public void pay(Orders order) {
        Orders old = orderDao.select(order.getId());
        old.setStatus(Orders.STATUS_PAYED);
        old.setPaytype(order.getPaytype());
        old.setName(order.getName());
        old.setPhone(order.getPhone());
        old.setAddress(order.getAddress());
        orderDao.update(old);
    }
    public List<Orders> getListByUserid(int userid,int page,int size){
        return pack(orderDao.selectListByUserId(userid,page*(size-1),size));
    }
    private Orders pack(Orders orders){
        if (Objects.nonNull(orders)){
            List<Items> itemsList=itemDao.selectList(orders.getId());
            for (Items items : itemsList) {
                items.setGood(goodService.get(items.getGoodId()));
            }
            orders.setItemList(itemsList);
            orders.setUser(userService.get(orders.getUserId()));
        }
        return orders;
    }
    public int getCountByUserid(int userId){
        return orderDao.selectCountByUserid(userId);
    }

}
