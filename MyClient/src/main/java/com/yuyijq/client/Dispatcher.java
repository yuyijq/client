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
        if (data.getValue().equals("begin")) {
            subscriber.onBegin();
        } else {
            subscriber.onReceive(data.getValue());
        }
    }

    public void register(Subscriber subscriber) {
        registeredSubscribers.put(subscriber.getQueryId(), subscriber);
    }
}
