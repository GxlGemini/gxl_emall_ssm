package com.entity;

/**
 * @Author linxiaobai
 * @Date 2020/9/21 16:30
 * @Description TODO
 * @Version 1.0
 **/
public class Goods {
    private int id;
    private String cover;
    private String name;
    private  String intro;
    private String spec;
    private int price;
    private  int stock;
    private int sales;
    private String content;
    private Types type;
    private int typeId;
    private boolean top;

    public Goods() {
    }

    public Goods(int id, String cover, String name, String intro, String spec, int price, int stock, int sales, String content, Types type, int typeId, boolean top) {
        this.id = id;
        this.cover = cover;
        this.name = name;
        this.intro = intro;
        this.spec = spec;
        this.price = price;
        this.stock = stock;
        this.sales = sales;
        this.content = content;
        this.type = type;
        this.typeId = typeId;
        this.top = top;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", spec='" + spec + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", sales=" + sales +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", typeId=" + typeId +
                ", top=" + top +
                '}';
    }
}
