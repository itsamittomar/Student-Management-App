package com.android.attendance.bean;

public class QueryBean  {
    private int query_id;
    private String query_user;
    private String query_regarding;
    private String query_query;

    public int getQuery_id() {
        return query_id;
    }

    public void setQuery_id(int query_id) {
        this.query_id = query_id;
    }

    public String getQuery_user() {
        return query_user;
    }

    public void setQuery_user(String query_user) {
        this.query_user = query_user;
    }

    public String getQuery_regarding() {
        return query_regarding;
    }

    public void setQuery_regarding(String query_regarding) {
        this.query_regarding = query_regarding;
    }

    public String getQuery_query() {
        return query_query;
    }

    public void setQuery_query(String query_query) {
        this.query_query = query_query;
    }
}
