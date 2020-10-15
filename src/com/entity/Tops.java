package com.entity;

/**
 * @Author linxiaobai
 * @Date 2020/9/24 9:54
 * @Description TODO
 * @Version 1.0
 **/
public class Tops {
    public static final byte TYPE_TODAY=1;

    private Integer id;
    private Integer type;
    private Integer goodId;

    public Tops() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    @Override
    public String toString() {
        return "Tops{" +
                "id=" + id +
                ", type=" + type +
                ", goodId=" + goodId +
                '}';
    }
}
