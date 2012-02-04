package com.yuyijq.client;

import java.util.EventListener;

/**
 * User: zhaohuiyu
 * Date: 2/3/12
 * Time: 10:08 PM
 */
public class ConnectedListener implements EventListener{
    private String url;

    public String getUrl() {
        return url;
    }

    public void connected(String url) {
        this.url = url;
    }
}
