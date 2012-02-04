package com.yuyijq.client;

import com.yuyijq.driver.MyDriverException;

/**
 * User: zhaohuiyu
 * Date: 2/4/12
 * Time: 7:58 PM
 */
public interface DriverClient {
    void connect() throws MyDriverException;

    void close();
}
