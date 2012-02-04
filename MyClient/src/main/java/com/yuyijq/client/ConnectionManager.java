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
        int current = 0;
        while (true) {
            MyDriverClient driver = driverFactory.createDriver(urls[current]);
            try {
                driver.connect();
                return driver;
            } catch (MyDriverException e) {
                ++current;
                continue;
            }
        }

    }
}
