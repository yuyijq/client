package com.yuyijq.client;

import com.yuyijq.driver.MyDriverClient;

/**
 * User: zhaohuiyu
 * Date: 2/3/12
 * Time: 9:55 PM
 */
public interface DriverFactory {
    MyDriverClient createDriver(String url);
}
