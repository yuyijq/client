package com.yuyijq.client;

public class RoundPollPolicy implements PollPolicy {
    private String[] urls;
    private int current = 0;

    public RoundPollPolicy(String[] urls) {
        this.urls = urls;
    }

    public String selectServer() {
        current = current % urls.length;
        String url = urls[current];
        ++current;
        return url;
    }
}
