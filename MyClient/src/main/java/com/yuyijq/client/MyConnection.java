package com.yuyijq.client;

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

    }

    public void addConnectedListener(ConnectedListener connectedListener) {
        connectedListeners.add(connectedListener);
    }
}
