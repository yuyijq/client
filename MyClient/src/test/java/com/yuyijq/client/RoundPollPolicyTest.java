package com.yuyijq.client;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoundPollPolicyTest {
    @Test
    public void should_return_first_server_given_first_call() {
        String[] urls = {"server1", "server2"};
        RoundPollPolicy pollPolicy = new RoundPollPolicy(urls);

        String url = pollPolicy.selectServer();

        assertThat(url, is("server1"));
    }

    @Test
    public void should_return_second_server_given_call_twice() {
        String[] urls = {"server1", "server2"};
        RoundPollPolicy pollPolicy = new RoundPollPolicy(urls);

        pollPolicy.selectServer();
        String url = pollPolicy.selectServer();

        assertThat(url,is("server2"));
    }

    @Test
    public void should_return_first_server_given_called_all_servers() {
        String[] urls = {"server1", "server2"};
        RoundPollPolicy pollPolicy = new RoundPollPolicy(urls);

        pollPolicy.selectServer();
        pollPolicy.selectServer();
        String url = pollPolicy.selectServer();

        assertThat(url,is("server1"));
    }

}
