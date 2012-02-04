package com.yuyijq.client;

/**
 * User: zhaohuiyu
 * Date: 2/3/12
 * Time: 9:55 PM
 */
public interface DriverFactory {
    DriverClient createDriver(String url);
}
