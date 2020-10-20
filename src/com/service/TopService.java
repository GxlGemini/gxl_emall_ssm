package com.service;

import com.dao.TopsDao;
import com.entity.Tops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author linxiaobai
 * @Date 2020/9/24 10:18
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class TopService {

    @Autowired
    private TopsDao topsDao;

    public Tops getByGoodIdAndType(int goodId,byte type){
        return topsDao.selectByGoodIdAndType(goodId, type);
    }


    public String getGoodIdsByType(byte type) {
        List<String> list=topsDao.selectGoodIdByType(type);
        return Objects.nonNull(list) ? String.join(",", list):null;
    }
}
