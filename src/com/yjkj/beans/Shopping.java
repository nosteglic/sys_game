package com.yjkj.beans;

public class Shopping {
    private Integer id;
    private Integer c_id;
    private Integer g_id;
    private String g_name;
    private String g_price;
    private String p_name;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_price() {
        return g_price;
    }

    public void setG_price(String g_price) {
        this.g_price = g_price;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "id=" + id +
                ", c_id=" + c_id +
                ", g_id=" + g_id +
                ", g_name='" + g_name + '\'' +
                ", g_price='" + g_price + '\'' +
                ", p_name='" + p_name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
