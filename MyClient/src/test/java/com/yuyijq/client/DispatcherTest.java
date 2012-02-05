package com.yuyijq.client;

import com.yuyijq.driver.MyData;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DispatcherTest {
    @Test
    public void should_delivery_begin_message_to_given_subscriber() {
        Dispatcher dispatcher = new Dispatcher();
        FakeSubscriber subscriber = new FakeSubscriber(1);
        dispatcher.register(subscriber);

        MyData data = new MyData(1,"begin");
        dispatcher.dispatch(data);

        assertThat(subscriber.getMessage(), is("begin"));
    }
}
