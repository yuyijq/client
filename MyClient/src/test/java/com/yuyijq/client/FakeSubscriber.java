package com.yuyijq.client;

public class FakeSubscriber implements Subscriber {
    private Integer queryId;
    private String message;
    private boolean begin = false;

    public FakeSubscriber(Integer queryId) {
        this.queryId = queryId;
    }

    public Integer getQueryId() {
        return queryId;
    }

    public void onBegin() {
        begin = true;
    }

    public void onReceive(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isBegin() {
        return begin;
    }
}
