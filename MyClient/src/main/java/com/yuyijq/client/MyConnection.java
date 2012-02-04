package com.yuyijq.client;

import com.yuyijq.driver.MyDriverClient;
import com.yuyijq.driver.MyDriverException;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class MyConnection {
    private String[] urls;
    private DriverFactory driverFactory;

    private List<EventListener> connectedListeners;

    public MyConnection(String[] urls, DriverFactory driverFactory) {
        this.urls = urls;
        this.driverFactory = driverFactory;

        connectedListeners = new ArrayList<EventListener>();
    }

    public void open() {
        MyDriverClient driver = driverFactory.createDriver(urls[0]);
        try {
            driver.connect();
            for (EventListener connectedListener : connectedListeners) {
                ((ConnectedListener)connectedListener).connected(urls[0]);
            }
            return;
        } catch (MyDriverException e) {
        }
    }

    public void addConnectedListener(ConnectedListener connectedListener) {
        connectedListeners.add(connectedListener);
    }
}
