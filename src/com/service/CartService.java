package com.service;

import com.dao.CartDao;
import com.entity.Carts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 15:13
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private GoodService goodService;

    public Integer getCount(Integer id) {
        return cartDao.selectSumAmountByUserId(id);
    }

    private List<Carts> pack(List<Carts> list) {
        for (Carts carts : list) {
            carts = pack(carts);
        }
        return list;
    }

    private Carts pack(Carts carts) {
        if (Objects.nonNull(carts)) {
            carts.setGood(goodService.get(carts.getGoodId()));
            carts.setTotal(carts.getAmount() * carts.getGood().getPrice());
        }
        return carts;
    }

    public int getTotal(Integer id) {
        int total=0;
        List<Carts> cartsList=this.getList(id);
        if (Objects.nonNull(cartsList)&& !cartsList.isEmpty()){
            for (Carts carts : cartsList) {
                total +=carts.getGood().getPrice() * carts.getAmount();
            }
        }
        return total;
    }

    public boolean save(int goodId, int userId) {
        Carts cart = cartDao.selectByUserIdAndGoodId(userId, goodId);
        if (Objects.nonNull(cart)) {
            return cartDao.updateAmount(cart.getId(), 1);
        }
        cart = new Carts();
        cart.setGoodId(goodId);
        cart.setUserId(userId);
        cart.setAmount(1);
        return cartDao.insert(cart);
    }

    public boolean delete(int id) {
        return cartDao.delete(id);
    }

    public List<Carts> getList(int userId) {
        return pack(cartDao.selectListByUserId(userId));
    }

    public boolean clean(Integer userId) {
        return cartDao.deleteByUserId(userId);
    }

    public boolean add(int id) {
        return cartDao.updateAmount(id, 1);
    }

    public boolean less(int id) {
        Carts carts=cartDao.select(id);
        if (carts.getAmount()<=1){
            return delete(id);
        }
        return cartDao.updateAmount(id, -1);
    }
}
