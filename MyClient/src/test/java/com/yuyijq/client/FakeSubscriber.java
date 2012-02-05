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

    public String getMessage() {
        return message;
    }

    public boolean isBegin() {
        return begin;
    }
}
