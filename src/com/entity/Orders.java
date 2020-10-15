package com.entity;

import java.util.Date;
import java.util.List;

/**
 * @Author linxiaobai
 * @Date 2020/10/12 11:42
 * @Description TODO
 * @Version 1.0
 **/
public class Orders {

    /*订单状态 - 未付款*/
    public static final byte STATUS_UNPAY = 1;
    /**/
    public static final byte STATUS_PAYED = 2;

    public static final byte STATUS_SEND=3;

    public static final byte STATUS_FINISH=4;

    public static final  byte PAYTYPE_WECHAT=1;
    public static final  byte PAYTYPE_ALIPAY=2;
    public static final  byte PAYTYPE_OFFLINE=3;

    private int id;
    private int total;
    private int amount;
    private byte status;
    private byte paytype;
    private String name;
    private String phone;
    private String address;
    private Date systime;
    private int userId;
    private Users user;
    private List<Items> itemList;

    public Orders() {
    }

    public Orders(int id, int total, int amount, byte status, byte paytype, String name, String phone, String address, Date systime, int userId, Users user, List<Items> itemList) {
        this.id = id;
        this.total = total;
        this.amount = amount;
        this.status = status;
        this.paytype = paytype;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.systime = systime;
        this.userId = userId;
        this.user = user;
        this.itemList = itemList;
    }

    public static byte getStatusUnpay() {
        return STATUS_UNPAY;
    }

    public static byte getStatusPayed() {
        return STATUS_PAYED;
    }

    public static byte getStatusSend() {
        return STATUS_SEND;
    }

    public static byte getStatusFinish() {
        return STATUS_FINISH;
    }

    public static byte getPaytypeWechat() {
        return PAYTYPE_WECHAT;
    }

    public static byte getPaytypeAlipay() {
        return PAYTYPE_ALIPAY;
    }

    public static byte getPaytypeOffline() {
        return PAYTYPE_OFFLINE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getPaytype() {
        return paytype;
    }

    public void setPaytype(byte paytype) {
        this.paytype = paytype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getSystime() {
        return systime;
    }

    public void setSystime(Date systime) {
        this.systime = systime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Items> getItemList() {
        return itemList;
    }

    public void setItemList(List<Items> itemList) {
        this.itemList = itemList;
    }
}
