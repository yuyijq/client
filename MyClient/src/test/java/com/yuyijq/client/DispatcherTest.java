package com.yuyijq.client;

import com.yuyijq.driver.MyData;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DispatcherTest {

    private Dispatcher dispatcher;
    private FakeSubscriber subscriber;

    @Before
    public void setUp() {
        dispatcher = new Dispatcher();
        subscriber = new FakeSubscriber(1);
        dispatcher.register(subscriber);
    }

    @Test
    public void should_delivery_begin_message_to_given_subscriber() {
        MyData data = new MyData(1, "begin");
        dispatcher.dispatch(data);

        assertThat(subscriber.isBegin(), is(true));
    }

    @Test
    public void should_not_delivery_begin_message_to_given_subscriber_which_queryId_does_not_match() {
        MyData data = new MyData(2, "begin");
        dispatcher.dispatch(data);

        assertThat(subscriber.isBegin(), is(false));
    }
}
