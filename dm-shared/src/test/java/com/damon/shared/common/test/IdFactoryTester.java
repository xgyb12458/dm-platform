package com.damon.shared.common.test;

import com.damon.shared.id.impl.SnowflakeIdFactory;
import com.damon.shared.id.impl.TimestampIdFactory;
import com.damon.shared.utils.ApplicationUtils;
import org.junit.Before;
import org.junit.Test;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author Damon S.
 */
public class IdFactoryTester {
    private SnowflakeIdFactory snowflakeIdFactory;
    private TimestampIdFactory timestampIdFactory;


    @Before
    public void setup() {
        snowflakeIdFactory = SnowflakeIdFactory.instance();
        timestampIdFactory = TimestampIdFactory.instance();
    }

    @Test
    public void testNextId() {
        System.out.println("<<<<<<<<<雪花ID[WorkerId = 0]: " + snowflakeIdFactory.nextId());
        System.out.println("<<<<<<<<<雪花ID[WorkerId = 1]: " + snowflakeIdFactory.nextId(IdFactoryTester.class));
        System.out.println("<<<<<<<<<时间戳ID[WorkerId = 2]: " + timestampIdFactory.nextId());
    }

    @Test
    public void testConcurrentNextId() {
        Runnable snowflakeRunner =
                () -> System.out.println(Thread.currentThread().getName() + ": " +
                        snowflakeIdFactory.nextId(IdFactoryTester.class));

        Runnable timestampRunner =
                () -> System.out.println(Thread.currentThread().getName() + ": " +
                        timestampIdFactory.nextId());

        int sfloop = 10;
        while (sfloop-- > 0) {
            new Thread(snowflakeRunner).start();
        }

        int tsloop = 10;
        while (tsloop-- > 0) {
            new Thread(timestampRunner).start();
        }
    }

    @Test
    public void testFindLocalIP() {
        try {
            System.out.println("<<<<<<<<<本机MAC: " + ApplicationUtils.findLocalMac());
            System.out.println("<<<<<<<<<本机IP: " + ApplicationUtils.findLocalIP());
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
