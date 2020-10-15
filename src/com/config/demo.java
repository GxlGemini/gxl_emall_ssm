package com.config;

import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        System.out.println("欢迎来到万年历**********************");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入年份");
        int year = sc.nextInt();
        System.out.println("请输入月份");
        int month = sc.nextInt();
        System.out.println("您输入的是"+year+"年"+month+"月");

        int sumDay =0;
        //先算1900年-2020年过去了m天,因为2020没过完所以只要取到2019即可
        for(int i = 1900;i<year;i++){
            if(year%400==0||(year%4==0&&year%100!=0)){
                sumDay += 366;
            }else{
                sumDay += 365;
            }
        }
        //2020年输入的几月几日是第几天
        for(int i = 1;i<month;i++){
            switch (i){
                case 2:
                    //先判断今年是不是闰年
                    if(year%400==0||(year%4==0&&year%100!=0)){
                        sumDay += 29;
                    }else{
                        sumDay += 28;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    sumDay += 30;
                    break;

                default:
                    sumDay += 31;
                    break;
            }
        }
        System.out.println("您输入的是"+year+"年"+month+"月1号星期"+(sumDay+1)%7);
        System.out.println("星期一\t星期二\t星期三\t星期四\t星期五\t星期六\t星期日");

        //接下来需要算出这个月1号前有多少个星期被空格占住位置
        //怎么算？需要知道这个月的第一天星期几
        //星期日他的余数为0，而在循环中无法进入，但是实际情况中，前面需要执行6次
        int weekDay = (sumDay+1)%7==0?7:(sumDay+1)%7;
        for(int i =1;i<weekDay;i++){
            System.out.print("\t");
        }

        //需要知道当前月有多少天
        int monthDay=0;//用来记录当前月有多少天
        switch (month) {
            case 2:
                //需要知道今年是不是闰年
                if((year%4==0&&year%100!=0)||(year%400==0)){
                    monthDay=29;
                }else{
                    monthDay=28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                monthDay=30;
                break;
            default:
                monthDay=31;
                break;
        }

        for(int i=1;i<=monthDay;i++){
            sumDay++;
            if(sumDay%7==0){
                System.out.println(i);
            }else{
                System.out.print(i+"\t");
            }
        }
    }
}

