package com.yjkj.beans;

import java.util.Date;

public class Change {
    private int change_id;
    private Date change_time;
    private int g_id;
    private String g_name;
    private String g_type;
    private float g_price;
    private float g_discount;
    private String g_introduction;
    private Date g_time;
    private int g_age;
    private int change_status;//0未处理 1已通过 -1不通过
    private int u_id;

    public int getChange_id() {
        return change_id;
    }

    public void setChange_id(int change_id) {
        this.change_id = change_id;
    }

    public Date getChange_time() {
        return change_time;
    }

    public void setChange_time(Date change_time) {
        this.change_time = change_time;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
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

    public Date getG_time() {
        return g_time;
    }

    public void setG_time(Date g_time) {
        this.g_time = g_time;
    }

    public int getG_age() {
        return g_age;
    }

    public void setG_age(int g_age) {
        this.g_age = g_age;
    }

    public int getChange_status() {
        return change_status;
    }

    public void setChange_status(int change_status) {
        this.change_status = change_status;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public Change(Date change_time, int g_id, String g_name, String g_type, float g_price, float g_discount, String g_introduction, Date g_time, int g_age, int change_status, int u_id) {
        this.change_time = change_time;
        this.g_id = g_id;
        this.g_name = g_name;
        this.g_type = g_type;
        this.g_price = g_price;
        this.g_discount = g_discount;
        this.g_introduction = g_introduction;
        this.g_time = g_time;
        this.g_age = g_age;
        this.change_status = change_status;
        this.u_id = u_id;
    }

    public Change(int change_id, Date change_time, int g_id, String g_name, String g_type, float g_price, float g_discount, String g_introduction, Date g_time, int g_age, int change_status, int u_id) {
        this.change_id = change_id;
        this.change_time = change_time;
        this.g_id = g_id;
        this.g_name = g_name;
        this.g_type = g_type;
        this.g_price = g_price;
        this.g_discount = g_discount;
        this.g_introduction = g_introduction;
        this.g_time = g_time;
        this.g_age = g_age;
        this.change_status = change_status;
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "Change{" +
                "change_id=" + change_id +
                ", change_time=" + change_time +
                ", g_id=" + g_id +
                ", g_name='" + g_name + '\'' +
                ", g_type='" + g_type + '\'' +
                ", g_price=" + g_price +
                ", g_discount=" + g_discount +
                ", g_introduction='" + g_introduction + '\'' +
                ", g_time=" + g_time +
                ", g_age=" + g_age +
                ", change_status=" + change_status +
                ", u_id=" + u_id +
                '}';
    }
}
