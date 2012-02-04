package com.yuyijq.client;

import com.yuyijq.driver.MyDriverClient;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ConnectionManagerTest {
    @Test
    public void should_open_connection_to_server1_success_and_dont_connect_to_server2() {
        String server1 = "server1";
        String server2 = "server2";

        String[] urls = new String[]{server1, server2};
        DriverFactory driverFactory = mock(DriverFactory.class);

        MyDriverClient driver1 = mock(MyDriverClient.class);
        when(driverFactory.createDriver(server1)).thenReturn(driver1);

        MyDriverClient driver2 = mock(MyDriverClient.class);
        when(driverFactory.createDriver(server2)).thenReturn(driver2);

        ConnectionManager connectionManager = new ConnectionManager(urls, driverFactory);

        MyDriverClient connectedDriverClient = connectionManager.connect();

        assertThat(connectedDriverClient,is(driver1));
    }
}
