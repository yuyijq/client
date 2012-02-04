package com.yuyijq.client;

import com.yuyijq.driver.MyDriverClient;
import com.yuyijq.driver.MyDriverException;

public class ConnectionManager {
    private String[] urls;
    private DriverFactory driverFactory;
    private PollPolicy pollPolicy;
    private Integer interval;
    private MyThread thread;

    public ConnectionManager(String[] urls, DriverFactory driverFactory,PollPolicy pollPolicy) {
        this.urls = urls;
        this.driverFactory = driverFactory;
        this.pollPolicy = pollPolicy;
    }

    public MyDriverClient connect() {
        while (true) {
            MyDriverClient driver = driverFactory.createDriver(pollPolicy.selectServer());
            try {
                driver.connect();
                return driver;
            } catch (MyDriverException e) {
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
