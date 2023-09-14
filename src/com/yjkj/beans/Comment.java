package com.yjkj.beans;

import java.util.Date;

public class Comment {
    private int comment_id;
    private int c_id;
    private String c_name;
    private int g_id;
    private String g_name;
    private String g_type;
    private String comment_content;
    private Date comment_time;

    public Comment(int comment_id, int c_id, String c_name, int g_id, String g_name, String g_type, String comment_content, Date comment_time) {
        this.comment_id = comment_id;
        this.c_id = c_id;
        this.c_name = c_name;
        this.g_id = g_id;
        this.g_name = g_name;
        this.g_type = g_type;
        this.comment_content = comment_content;
        this.comment_time = comment_time;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
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

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }
}
