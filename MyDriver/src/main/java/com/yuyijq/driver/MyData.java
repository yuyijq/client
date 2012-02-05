package com.yuyijq.driver;

/**
 * User: zhaohuiyu
 * Date: 2/4/12
 * Time: 11:06 PM
 */
public class MyData {

    private String value;
    private Integer queryId;

    public MyData(Integer queryId, String value) {
        this.queryId = queryId;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Integer getQueryId() {
        return queryId;
    }
}