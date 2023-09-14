package com.yjkj.beans;

import java.util.Date;

public class Put {

    private int put_id;
    private Date put_time;
    private String g_name;
    private String g_type;
    private float g_price;
    private float g_discount;
    private String g_introduction;
    private int g_age;
    private int put_status;//0未处理 1已通过 -1未通过
    private int u_id;


    public int getPut_id() {
        return put_id;
    }

    public void setPut_id(int put_id) {
        this.put_id = put_id;
    }

    public Date getPut_time() {
        return put_time;
    }

    public void setPut_time(Date put_time) {
        this.put_time = put_time;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_type() {
        return g_type;
    }

    public void setG_type(String g_type) {
        this.g_type = g_type;
    }

    public float getG_price() {
        return g_price;
    }

    public void setG_price(float g_price) {
        this.g_price = g_price;
    }

    public float getG_discount() {
        return g_discount;
    }

    public void setG_discount(float g_discount) {
        this.g_discount = g_discount;
    }

    public String getG_introduction() {
        return g_introduction;
    }

    public void setG_introduction(String g_introduction) {
        this.g_introduction = g_introduction;
    }

    public int getG_age() {
        return g_age;
    }

    public void setG_age(int g_age) {
        this.g_age = g_age;
    }

    public int getPut_status() {
        return put_status;
    }

    public void setPut_status(int put_status) {
        this.put_status = put_status;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "Put{" +
                "put_id=" + put_id +
                ", put_time=" + put_time +
                ", g_name='" + g_name + '\'' +
                ", g_type='" + g_type + '\'' +
                ", g_price=" + g_price +
                ", g_discount=" + g_discount +
                ", g_introduction='" + g_introduction + '\'' +
                ", g_age=" + g_age +
                ", put_status=" + put_status +
                ", u_id=" + u_id +
                '}';
    }

    public Put(Date put_time, String g_name, String g_type, float g_price, float g_discount, String g_introduction, int g_age, int put_status, int u_id) {
        this.put_time = put_time;
        this.g_name = g_name;
        this.g_type = g_type;
        this.g_price = g_price;
        this.g_discount = g_discount;
        this.g_introduction = g_introduction;
        this.g_age = g_age;
        this.put_status = put_status;
        this.u_id = u_id;
    }

    public Put(int put_id, Date put_time, String g_name, String g_type, float g_price, float g_discount, String g_introduction, int g_age, int put_status, int u_id) {
        this.put_id = put_id;
        this.put_time = put_time;
        this.g_name = g_name;
        this.g_type = g_type;
        this.g_price = g_price;
        this.g_discount = g_discount;
        this.g_introduction = g_introduction;
        this.g_age = g_age;
        this.put_status = put_status;
        this.u_id = u_id;
    }
}
