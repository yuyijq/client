package com.yuyijq.client;

import com.yuyijq.driver.MyDriverException;

public class ConnectionManager {
    private static final Long DEFAULT_PAUSE_INTERVAL = 1L;

    private DriverFactory driverFactory;
    private PollPolicy pollPolicy;

    private Long interval;
    private MyThread thread;

    public ConnectionManager(DriverFactory driverFactory, PollPolicy pollPolicy) {
        init();
        this.driverFactory = driverFactory;
        this.pollPolicy = pollPolicy;
    }

    private void init() {
        interval = DEFAULT_PAUSE_INTERVAL;
    }


    public DriverClient connect() {
        while (true) {
            DriverClient driver = driverFactory.createDriver(pollPolicy.selectServer());
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

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public void setThread(MyThread thread) {
        this.thread = thread;
    }
}
