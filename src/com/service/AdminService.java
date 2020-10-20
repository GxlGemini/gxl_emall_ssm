package com.service;

import com.dao.AdminsDao;
import com.entity.Admins;
import com.util.SafeUtil;
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


    public long getCount() {
        return adminDao.selectCount();
    }

    public Admins getByUsername(String username) {
        return adminDao.selectByUsername(username);
    }

    public Admins getByUsernameAndPassword(String username, String password) {
        return adminDao.selectByUsernameAndPassword(username,SafeUtil.encode(password));
    }

    public boolean add(Admins admins) {
        admins.setPassword(SafeUtil.encode(admins.getPassword()));
        return false;
    }


}
