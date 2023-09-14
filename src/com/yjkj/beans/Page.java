package com.yjkj.beans;

public class Page {
    //当前页码
    private int current=1;  //
    //显示数据的上限
    private int limit=5;  //
    private int total=0;

    //返回给页面

    //数据总数 [用于计算总页数
    private int rows;  //
//    //查询路径[用于复用分页链接?
//    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>=1&&limit<=100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>=0) {
            this.rows = rows;
        }
    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }

    //获取当前页的起始行
    public int getOffset(){
        //(current-1)*limit
        return (current-1)*limit;
    }


    public void setTotal(int rows,int limit){
        //rows/limit+1
        if(rows%limit==0){
            this.total= rows/limit;
        }
        else{
            this.total= rows/limit+1;
        }
    }
    //用来获取总的页数 返回给页面上的
    public int getTotal(){
        return this.total;
    }

    //从第几页到第几页的页码[?
    public int getFrom(){
        int from=current-2;
        return from<1?1:from;

    }
    public int getTo(){
        int to=current+2;
        return to>getTotal()?getTotal():to;

    }
}

