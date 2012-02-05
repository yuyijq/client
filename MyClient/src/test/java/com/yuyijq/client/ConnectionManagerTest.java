package com.yuyijq.client;

import com.yuyijq.driver.MyDriverException;
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

        DriverClient driver1 = mock(DriverClient.class);
        when(driverFactory.createDriver(server1)).thenReturn(driver1);

        DriverClient driver2 = mock(DriverClient.class);
        when(driverFactory.createDriver(server2)).thenReturn(driver2);

        PollPolicy pollPolicy = new RoundPollPolicy(urls);
        ConnectionManager connectionManager = new ConnectionManager(driverFactory, pollPolicy);

        DriverClient connectedDriverClient = connectionManager.connect();

        assertThat(connectedDriverClient, is(driver1));
    }

    @Test
    public void should_connect_to_server2_given_server1_connect_failed() throws MyDriverException {
        String server1 = "server1";
        String server2 = "server2";

        String[] urls = new String[]{server1, server2};
        DriverFactory driverFactory = mock(DriverFactory.class);

        DriverClient driver1 = mock(DriverClient.class);
        doThrow(new MyDriverException()).when(driver1).connect();
        when(driverFactory.createDriver(server1)).thenReturn(driver1);

        DriverClient driver2 = mock(DriverClient.class);
        when(driverFactory.createDriver(server2)).thenReturn(driver2);

        PollPolicy pollPolicy = new RoundPollPolicy(urls);
        ConnectionManager connectionManager = new ConnectionManager(driverFactory, pollPolicy);
        Long interval = 2L;
        connectionManager.setInterval(interval);
        MyThread myThread = mock(MyThread.class);
        connectionManager.setThread(myThread);


        DriverClient connectedDriverClient = connectionManager.connect();

        assertThat(connectedDriverClient, is(driver2));
    }

    @Test
    public void should_pause_a_while_given_connect_to_server_failed() throws MyDriverException {
        String server1 = "server1";
        String server2 = "server2";

        String[] urls = new String[]{server1, server2};
        DriverFactory driverFactory = mock(DriverFactory.class);

        DriverClient driver1 = mock(DriverClient.class);
        doThrow(new MyDriverException()).when(driver1).connect();
        when(driverFactory.createDriver(server1)).thenReturn(driver1);

        DriverClient driver2 = mock(DriverClient.class);
        when(driverFactory.createDriver(server2)).thenReturn(driver2);

        PollPolicy pollPolicy = new RoundPollPolicy(urls);
        ConnectionManager connectionManager = new ConnectionManager(driverFactory, pollPolicy);
        Long interval = 2L;
        connectionManager.setInterval(interval);
        MyThread myThread = mock(MyThread.class);
        connectionManager.setThread(myThread);

        connectionManager.connect();

        verify(myThread, times(1)).sleep(interval);
    }

    @Test
    public void should_dispose_driverClient_given_connect_to_server_failed() throws MyDriverException {
        String server1 = "server1";
        String server2 = "server2";

        String[] urls = new String[]{server1, server2};
        DriverFactory driverFactory = mock(DriverFactory.class);

        DriverClient driver1 = mock(DriverClient.class);
        doThrow(new MyDriverException()).when(driver1).connect();
        when(driverFactory.createDriver(server1)).thenReturn(driver1);

        DriverClient driver2 = mock(DriverClient.class);
        when(driverFactory.createDriver(server2)).thenReturn(driver2);

        PollPolicy pollPolicy = new RoundPollPolicy(urls);
        ConnectionManager connectionManager = new ConnectionManager(driverFactory, pollPolicy);
        Long interval = 2L;
        connectionManager.setInterval(interval);
        MyThread myThread = mock(MyThread.class);
        connectionManager.setThread(myThread);

        connectionManager.connect();

        verify(driver1, times(1)).close();
    }

    @Test
    public void should_retry_first_server_given_all_server_connect_failed() {

    }
}
