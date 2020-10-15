package com.service;

import com.dao.TypeDao;
import com.entity.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 14:45
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class TypeService {

        @Autowired
        private TypeDao typeDao;

        public List<Types> getList(){
            return typeDao.selectList();
        }

        public Types get(int id){
            return typeDao.select(id);
        }

      /*  public Tops getByGoodIdAndType(int goodId,byte type){
            return typeDao.selectByGoodIdAndType(goodId,type);
        }*/


}
