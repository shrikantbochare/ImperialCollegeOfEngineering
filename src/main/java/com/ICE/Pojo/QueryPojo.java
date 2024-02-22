package com.ICE.Pojo;

import jakarta.persistence.Column;

import java.sql.Date;

public class QueryPojo {

    private int id;

    private String title;

    private String query;

    private Date postedDate;

    private String remark;


    public QueryPojo(String title, String query, Date postedDate) {
        this.title = title;
        this.query = query;
        this.postedDate = postedDate;
    }


    public QueryPojo() {
    }


    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
