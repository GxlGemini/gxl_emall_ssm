package com.service;

import com.dao.GoodDao;
import com.entity.Goods;
import com.entity.Tops;
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
public class GoodService {

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TopService topService;

    private List<Goods> packGood(List<Goods> list) {
        for (Goods good : list) {
            good = packGood(good);
        }
        return list;
    }


    private Goods packGood(Goods good) {
        if (good != null) {
            good.setType(typeService.get(good.getTypeId()));
            good.setTop(Objects.nonNull(topService.getByGoodIdAndType(good.getId(), Tops.TYPE_TODAY)));
        }
        return good;
    }

    public List<Goods> getListByType(int typeId, int page, int size) {
        return typeId > 0 ? packGood(goodDao.selectListByTypeId(typeId, size * (page - 1), size)) : this.getList(page, size);
    }

    public List<Goods> getList(int page, int size) {
        return packGood(goodDao.selectList(size*(page-1), size));
    }


    public long getCountByType(int typeId) {
        return typeId >0?goodDao.selectCountByTypeId(typeId):this.getCount();
    }

    public int getCount(){
        return goodDao.selectCount();
    }

    public List<Goods> getListByTopType(byte typeToday, int page, int size) {
        return packGood(goodDao.selectListByToType(typeToday,size*(page-1),size));
    }
    public long getCountByTopType(byte type){
        return goodDao.selectCountByTopType(type);
    }

    public List<Goods> getListOrderSales(int page, int size) {
        return packGood(goodDao.selectListOrderSales(size*(page-1), size));
    }

    public Goods get(int id) {
        return packGood(goodDao.select(id));
    }

    public boolean updateStock(int id, int stock) {
        return goodDao.updateStock(id,stock);
    }

    public boolean updateSales(int id, int stock) {
        return goodDao.updateSales(id,stock);
    }
    public boolean update(Goods good){
        return goodDao.update(good);
    }
}
