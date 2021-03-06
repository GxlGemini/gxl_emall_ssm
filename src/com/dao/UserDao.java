package com.dao;

import com.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 15:19
 * @Description TODO
 * @Version 1.0
 **/
public interface UserDao {

    @Select("select * from users order by id desc limit #{begin},#{size}")
    public List<Users> selectList(@Param("begin")int begin, @Param("size")int size);

    @Select("select * from users where id=#{id}")
    public Users select(int id);

    @Select("select * from users where username=#{username}")
    public Users selectByUsername(String username);

    @Select("select * from users where username=#{username} and password=#{password}")
    public Users selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Insert("insert into users(username,password,name,phone,address) " + "values (#{username},#{password},#{name},#{phone},#{address})")
    @SelectKey(keyProperty = "id", statement = "SELECT LAST_INSERT_ID()", before = false, resultType = Integer.class)
    public boolean insert(Users users);

    @Update("update users set name=#{name},phone=#{phone},address=#{address} where id=#{id}")
    public boolean update(Users users);

    @Update("update users set password=#{password} where id=#{id}")
    public boolean updatePassword(@Param("id")int id,@Param("password")String password);

    @Update("delete from users where id=#{id}")
    public boolean delete(int id);
}
