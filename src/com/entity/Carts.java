package com.entity;

/**
 * @Author linxiaobai
 * @Date 2020/9/29 10:16
 * @Description TODO
 * @Version 1.0
 **/
public class Carts {
    private int id;
    private int amount;
    private int goodId;
    private int userId;
    private int total;
    private Goods good;

    public Carts() {
    }

    public Carts(int id, int amount, int goodId, int userId, int total, Goods good) {
        this.id = id;
        this.amount = amount;
        this.goodId = goodId;
        this.userId = userId;
        this.total = total;
        this.good = good;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "id=" + id +
                ", amount=" + amount +
                ", goodId=" + goodId +
                ", userId=" + userId +
                ", total=" + total +
                ", good=" + good +
                '}';
    }
}
