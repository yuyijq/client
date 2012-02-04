package com.yuyijq.client;

public class MyConnection {
    private String[] urls;
    private DriverFactory driverFactory;

    public MyConnection(String[] urls, DriverFactory driverFactory) {
        this.urls = urls;
        this.driverFactory = driverFactory;

    }

    public void open() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                ConnectionManager connectionManager = new ConnectionManager(driverFactory, new RoundPollPolicy(urls));
                connectionManager.connect();
            }
        });
        thread.start();
    }

}
