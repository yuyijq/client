package com.yuyijq.client;

public interface Subscriber {
    Integer getQueryId();

    void onBegin();
}
