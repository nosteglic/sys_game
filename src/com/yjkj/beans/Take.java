package com.yjkj.beans;

import java.util.Date;

public class Take {
    private int take_id;
    private Date take_time;
    private int g_id;
    private String g_name;
    private String take_reason;
    private int take_status;//0未处理 1已通过 -1未通过
    private int u_id;

    public int getTake_id() {
        return take_id;
    }

    public void setTake_id(int take_id) {
        this.take_id = take_id;
    }

    public Date getTake_time() {
        return take_time;
    }

    public void setTake_time(Date take_time) {
        this.take_time = take_time;
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

    public String getTake_reason() {
        return take_reason;
    }

    public void setTake_reason(String take_reason) {
        this.take_reason = take_reason;
    }

    public int getTake_status() {
        return take_status;
    }

    public void setTake_status(int take_status) {
        this.take_status = take_status;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "Take{" +
                "take_id=" + take_id +
                ", take_time=" + take_time +
                ", g_id=" + g_id +
                ", g_name='" + g_name + '\'' +
                ", take_reason='" + take_reason + '\'' +
                ", take_status=" + take_status +
                ", u_id=" + u_id +
                '}';
    }

    public Take(Date take_time, int g_id, String g_name, String take_reason, int take_status, int u_id) {
        this.take_time = take_time;
        this.g_id = g_id;
        this.g_name = g_name;
        this.take_reason = take_reason;
        this.take_status = take_status;
        this.u_id = u_id;
    }

    public Take(int take_id, Date take_time, int g_id, String g_name, String take_reason, int take_status, int u_id) {
        this.take_id = take_id;
        this.take_time = take_time;
        this.g_id = g_id;
        this.g_name = g_name;
        this.take_reason = take_reason;
        this.take_status = take_status;
        this.u_id = u_id;
    }
}
