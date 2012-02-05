package com.yuyijq.client;

public class FakeSubscriber implements Subscriber {
    private Integer queryId;
    private String message;

    public FakeSubscriber(Integer queryId) {
        this.queryId = queryId;
    }

    public Integer getQueryId() {
        return queryId;
    }

    public String getMessage() {
        return "begin";
    }
}
