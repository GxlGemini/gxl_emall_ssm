package com.dao;

import com.entity.Carts;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 15:19
 * @Description TODO
 * @Version 1.0
 **/
public interface CartDao {
    @Select("select count(*) from carts")
    public int getCount(int id);

    @Select("select * from carts where id=#{id}")
    Carts select(@Param("id") int id);

    @Select("select * from carts where user_id=#{userId} and good_id=#{goodId} limit 1")
    Carts selectByUserIdAndGoodId(@Param("userId") int userId, @Param("goodId") int goodId);

    @Delete("delete from carts where user_id=#{goodId}")
    public boolean deleteByUserid(int goodId);

    @Select("select * from carts where user_id=#{userId}")
    List<Carts> selectListByUserId(@Param("userId") int userId);

    @Update("update carts set amount=amount+#{amount} where id=#{id}")
    public boolean updateAmount(@Param("id") int id, @Param("amount") int amount);

    @Insert("insert into carts (amount,good_id,user_id) values (#{amount},#{goodId},#{userId})")
    @SelectKey(keyProperty = "id", statement = "SELECT LAST_INSERT_ID()", before = false, resultType = Integer.class)
    public boolean insert(Carts carts);

    @Select("select count(*) from carts where user_id=#{userId}")
    int selectCountByUserId(@Param("userId") int userId);

    @Select("select ifnull(sum(amount),0) from carts where user_id=#{userId}")
    int selectSumAmountByUserId(@Param("userId")int userId);

    @Update("delete from carts where id=#{id}")
    public  boolean delete(int id);

    @Update("delete from carts where user_id=#{userId}")
    public boolean deleteByUserId(int userId);
}
