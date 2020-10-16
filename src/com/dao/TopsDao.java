package com.dao;

import com.entity.Tops;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/9/24 9:50
 * @Description TODO
 * @Version 1.0
 **/
public interface TopsDao {

    @Select("select good_id from tops where type=#{type}")
    public List<String> selectGoodIdByType(byte type);

    @Select("select * from tops where good_id=#{goodId} and type=#{type}")
    public Tops selectByGoodIdAndType(@Param("goodId") int goodId, @Param("type") byte type);

    @Insert("insert into tops (type,good_id) values (#{type},#{goodId})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",before = false,resultType = Integer.class)
    public boolean insert(Tops top);

    @Update("delete from tops where id=#{id}")
    public boolean delete(int id);

    @Delete("delete from tops where good_id=#{goodId}")
    public boolean delete();
}
