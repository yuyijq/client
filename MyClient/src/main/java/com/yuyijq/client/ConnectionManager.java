package com.yuyijq.client;

import com.yuyijq.driver.MyDriverClient;
import com.yuyijq.driver.MyDriverException;

public class ConnectionManager {
    private String[] urls;
    private DriverFactory driverFactory;

    public ConnectionManager(String[] urls, DriverFactory driverFactory) {
        this.urls = urls;
        this.driverFactory = driverFactory;
    }

    public MyDriverClient connect() {
        MyDriverClient driver = driverFactory.createDriver(urls[0]);
        try {
            driver.connect();
            return driver;
        } catch (MyDriverException e) {
            return null;
        }
    }
}
