package com.yuyijq.client;

import com.yuyijq.driver.MyDriverClient;
import com.yuyijq.driver.MyDriverException;

public class ConnectionManager {
    private String[] urls;
    private DriverFactory driverFactory;
    private Integer interval;
    private MyThread thread;

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
                driver.close();
                pauseAWhile();
                continue;
            }
        }

    }

    private void pauseAWhile() {
        thread.sleep(interval);
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public void setThread(MyThread thread) {
        this.thread = thread;
    }
}
