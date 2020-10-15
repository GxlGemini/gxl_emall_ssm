package com.dao;

import com.entity.Admins;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
