package com.service;

import com.dao.UserDao;
import com.entity.Users;
import com.util.SafeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author linxiaobai
 * @Date 2020/9/18 10:57
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Users getByUsername(String username){
        return userDao.selectByUsername(username);
    }
    public boolean add(Users user){
        user.setPassword(SafeUtil.encode(user.getPassword()));
        return userDao.insert(user);
    }
    public boolean update(int id,String name,String phone,String address){
        Users user=new Users();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);
        return userDao.update(user);
    }

    public Users getByUsernameAndPassword(String username,String password){
        return userDao.selectByUsernameAndPassword(username,SafeUtil.encode(password));
    }
    public Users get(int id){
        return userDao.select(id);
    }
    public boolean updatePassword(int id, String passwordNer){
        return userDao.updatePassword(id,SafeUtil.encode(passwordNer));
    }

}
