package com.dao;

import com.entity.Types;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 15:19
 * @Description TODO
 * @Version 1.0
 **/
public interface TypeDao {

    @Select("select * from types order by num")
    public List<Types> selectList();

    @Select("select * from types where id=#{id}")
    public Types select(int id);

    @Insert("insert into types (name,num) values (#{name},#{num})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",before = false,resultType = Integer.class)
    public boolean insert(Types types);

    @Update("update types set name=#{name},num=#{num} where id=#{id})")
    public boolean update(Types types);

    @Update("delete from  types where id=#{id}")
    public boolean delete(int id);

}
