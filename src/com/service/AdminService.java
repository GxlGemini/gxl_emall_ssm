package com.service;

import com.dao.AdminsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 14:44
 * @Description TODO
 * @Version 1.0
 **/

@Service
public class AdminService {




    @Autowired
    private AdminsDao adminDao;


    public long getCount(){
        return adminDao.selectCount();
    }
}
