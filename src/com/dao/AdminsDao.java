package com.dao;

import com.entity.Admins;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 15:19
 * @Description TODO
 * @Version 1.0
 **/
public interface AdminsDao {

    @Select("select count(*) from admins")
    public int selectCount();

    @Select("select * from admins order by id desc limit #{begin}, #{size}")
    public List<Admins> selectList(@Param("begin") int begin, @Param("size") int size);

    @Select("select * from admins where id=#{id}")
    public Admins select(int id);

    @Select("select * from admins where username=#{username}")
    public Admins selectByUsername(String username);

    @Select("select * from admins where username=#{username} and password=#{password}")
    public Admins selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Insert("insert into admins (username,password) values (#{username},#{password})")
    @SelectKey(keyProperty = "id", statement = "SELECT LAST_INSERT_ID()", before = false, resultType = Integer.class)
    public boolean insert(Admins admins);

    @Update("update admins set password=#{password} where id=#{id}")
    public boolean update(@Param("id") int id, @Param("password") String password);

    @Update("delete from admins where id=#{id}")
    public boolean delete(int id);
}
