package com.yuyijq.client;

/**
 * User: zhaohuiyu
 * Date: 2/5/12
 * Time: 11:34 AM
 */
public class OSThread implements MyThread {
    public void sleep(Long interval) {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {

        }
    }
}
