package com.dao;

import com.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/9/8 15:19
 * @Description TODO
 * @Version 1.0
 **/
public interface GoodDao {

    @Select("select count(*) from goods")
    public int selectCount();

    @Select("select * from goods where id in (${ids}) order by id desc limit #{begin}, #{size}")
    public List<Goods> selectListByIds(@Param("ids") String ids, @Param("begin") int begin,
                                       @Param("size") int size);

    @Select("select * from goods where type_id=#{typeId} order by id desc limit #{begin},#{size}")
    public List<Goods> selectListByTypeId(@Param("typeId") int typeId, @Param("begin") int begin, @Param("size") int size);

    @Select("select * from goods g join tops t on g.id=t.good_id where t.type=#{type} order by t.id desc limit #{begin}, #{size}")
    public List<Goods> selectListByToType(@Param("type") byte type, @Param("begin") int begin, @Param("size") int size);

    @Select("select * from goods where id=#{id}")
    public Goods select(int id);

    @Select("select count(*) from goods where type_id=#{typeId}")
    public int selectCountByTypeId(@Param("typeId") int typeId);

    @Select("select count(*) from goods where id in (${ids})")
    public int selectCountByIds(@Param("ids") String ids);

    @Select("select count(*) from goods g join tops t on g.id=t.good_id where t.type=#{type}")
    public int selectCountByTopType(@Param("type") byte type);

    @Select("select count(*) from goods where name like concat('%',#{name},'%')")
    public int selectCountByName(@Param("name") String name);

    @Select("select * from goods order by id desc limit #{begin},#{size}")
    public List<Goods> selectList(@Param("begin") int begin, @Param("size") int size);

    @Select("select * from goods order by sales desc limit #{begin}, #{size}")
    public List<Goods> selectListOrderSales(@Param("begin") int begin, @Param("size") int size);

    @Select("select * from goods order by systime desc limit #{begin},#{size}")
    public List<Goods> selectListOrderTime(@Param("begin") int begin, @Param("size") int size);

    @Update("delete from goods where id=#{id}")
    public boolean delete(int id);

    @Update("update goods set sales=sales+#{sales} where id=#{id}")
    public boolean updateSales(@Param("id") int id, @Param("sales") int sales);

    @Update("update goods set stock=stock-#{stock} where id=#{id}")
    public boolean updateStock(@Param("id") int id, @Param("stock") int stock);

    @Update("update goods set cover=#{cover},name=#{name},intro=#{intro},spec=#{spec},"
            + "content=#{content},price=#{price},stock=#{stock},type_id=#{typeId} where id=#{id}")
    public boolean update(Goods good);

    @Insert("insert into goods (cover,name,intro,spec,content,price,stock,type_id)"
    +"values (#{cover},#{name},#{intro},#{spec},#{content},#{price},#{stock},#{typeId})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",before = false,resultType = Integer.class)
    public boolean insert(Goods goods);
}
