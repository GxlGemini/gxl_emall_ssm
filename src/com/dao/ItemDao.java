package com.dao;

import com.entity.Items;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/10/13 19:56
 * @Description TODO
 * @Version 1.0
 **/
public interface ItemDao {

    @Select("select * from items where order_id=#{orderId}")
    public List<Items> selectList(int orderId);

    @Insert("insert into items (price,amount,order_id,good_id) values(#{price},#{amount},#{orderId},#{goodId})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",before = false,resultType = Integer.class)
    public boolean inset(Items item);
}
