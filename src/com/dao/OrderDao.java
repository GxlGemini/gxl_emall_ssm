package com.dao;

import com.entity.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 15:19
 * @Description TODO
 * @Version 1.0
 **/
public interface OrderDao {


    @Select("select * from orders where id=#{id}")
    public Orders select(int id);

    @Insert("insert into orders (total,amount,status,paytype,name,phone,address,systime,user_id)" +
            "values (#{total},#{amount},#{status},#{paytype},#{name},#{phone},#{address},now(),#{userId})")
    @SelectKey(keyProperty = "id", statement = "SELECT LAST_INSERT_ID()", before = false, resultType = Integer.class)
    public boolean insert(Orders order);

    @Update("update orders set status=#{status},paytype=#{paytype},name=#{name},phone=#{phone},address=#{address} where id=#{id}")
    public boolean update(Orders order);

    @Update("update orders set status=#{status} where id=#{id}")
    public boolean updateStatus(@Param("id") int id, @Param("status") byte status);

    @Update("delete from orders where id=#{id}")
    public boolean delete(int id);

    @Select("select count(*) from orders where status=#{status}")
    public int selectCountByStatus(@Param("status")byte status);

    @Select("select count(*) from orders where user_id=#{userId}")
    public  int selectCountByUserid(@Param("userId") int userId);

    @Select("select * from orders order by id desc limit #{begin}, #{size}")
    public List<Orders> selectList(@Param("begin")int begin,@Param("size")int size);

    @Select("select * from orders where user_id=#{userId} order by id desc limit #{begin},#{size}")
    public List<Orders> selectListByUserId(@Param("userId")int userId,@Param("begin")int begin,@Param("size")int size);
}
