package com.had.Multiplayer.collaboration.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @ClassName ResultWithPage
 * @Description TODO
 * @Author had
 * @Date 2021/1/14 23:54
 * @Version 1.0
 **/
public class ResultWithPage<T> extends PageInfo {
    private String status;
    private String msg;
    private List<T> data;
    @JsonIgnore
    private Boolean isLogin;
    private int totalPage;
    private int page;
    private long total;
    @JsonIgnore
    private int size;
    @JsonIgnore
    private int firstPage;
    @JsonIgnore
    private int lastPage;
    @JsonIgnore
    private int startRow;
    @JsonIgnore
    private int endRow;
    @JsonIgnore
    private int pages;
    @JsonIgnore
    private int prePage;
    @JsonIgnore
    private int nextPage;
    @JsonIgnore
    private boolean isLastPage;
    @JsonIgnore
    private boolean isFirstPage;
    @JsonIgnore
    private boolean hasNextPage;
    @JsonIgnore
    private boolean hasPreviousPage;
    @JsonIgnore
    private int navigatePages;
    @JsonIgnore
    private int navigateFirstPage;
    @JsonIgnore
    private int navigateLastPage;
    @JsonIgnore
    private int pageNum;
    @JsonIgnore
    private int pageSize;
    @JsonIgnore
    private List<T> list;
    @JsonIgnore
    private int[] navigatepageNums;


    public ResultWithPage (String status, String msg, PageInfo pageInfo) {
        this.status = status;
        this.msg = msg;
        this.total = pageInfo.getTotal();
        this.totalPage = pageInfo.getPages();
        this.page = pageInfo.getPageNum();
        this.data = pageInfo.getList();
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public void setTotal(long total) {
        this.total = total;
    }

    public ResultWithPage() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
