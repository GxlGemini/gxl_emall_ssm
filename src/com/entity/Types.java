package com.entity;

/**
 * @Author linxiaobai
 * @Date 2020/9/22 9:20
 * @Description TODO
 * @Version 1.0
 **/
public class Types {

    private Integer id;
    private String name;
    private Integer num;

    public Types() {
    }

    public Types(Integer id, String name, Integer num) {
        this.id = id;
        this.name = name;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Types{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num=" + num +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
