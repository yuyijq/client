package com.yuyijq.client;

import com.yuyijq.driver.MyData;
import com.yuyijq.driver.MyDriverException;

public class MyConnection {
    private String[] urls;
    private DriverFactory driverFactory;
    private DriverClient driverClient;
    private Dispatcher dispatcher;

    public MyConnection(String[] urls, DriverFactory driverFactory) {
        this.urls = urls;
        this.driverFactory = driverFactory;

    }

    public void open() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                dispatch();
            }
        });
        thread.start();
    }

    private void dispatch() {
        ConnectionManager connectionManager = new ConnectionManager(driverFactory, new RoundPollPolicy(urls));
        driverClient = connectionManager.connect();

        while (true) {
            MyData data = receive(connectionManager);
            if(data != null){
                dispatcher.dispatch(data);
            }
        }
    }

    private MyData receive(ConnectionManager connectionManager) {
        try {
            return  driverClient.receive();
        } catch (MyDriverException e) {
            driverClient = connectionManager.connect();
            return null;
        }
    }

    public Integer subscribe(Subscriber subscriber){
        dispatcher.register(subscriber);
        return subscriber.getQueryId();
    }

}
