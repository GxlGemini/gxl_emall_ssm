package com.entity;

/**
 * @Author linxiaobai
 * @Date 2020/9/17 10:38
 * @Description 部门信息实体类
 * @Version 1.0
 **/
public class Mydept {
    private Integer pid;
    private String pname;
    private String premark;
    private Integer pflag;

    public Mydept(Integer pid, String pname, String premark, Integer pflag) {
        this.pid = pid;
        this.pname = pname;
        this.premark = premark;
        this.pflag = pflag;
    }

    public Mydept() {
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPremark() {
        return premark;
    }

    public void setPremark(String premark) {
        this.premark = premark;
    }

    public Integer getPflag() {
        return pflag;
    }

    public void setPflag(Integer pflag) {
        this.pflag = pflag;
    }

    @Override
    public String toString() {
        return "Mydept{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", premark='" + premark + '\'' +
                ", pflag=" + pflag +
                '}';
    }
}
