package com.yuyijq.client;

import com.yuyijq.driver.MyData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Dispatcher {
    private Map<Integer, Subscriber> registeredSubscribers = new ConcurrentHashMap<Integer, Subscriber>();

    public void dispatch(MyData data) {
        Subscriber subscriber = registeredSubscribers.get(data.getQueryId());
        if (subscriber == null) {
            return;
        }
        if (isBegin(data)) {
            subscriber.onBegin();
        } else {
            subscriber.onReceive(data.getValue());
        }
    }

    private boolean isBegin(MyData data) {
        return data.getValue().equals("begin");
    }

    public void register(Subscriber subscriber) {
        registeredSubscribers.put(subscriber.getQueryId(), subscriber);
    }

    public void deregister(Integer subscribeId) {

    }
}
