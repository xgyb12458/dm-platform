package com.damon.shared.common.test;

import com.damon.shared.common.IdFactory;
import com.damon.shared.utils.ApplicationUtils;
import org.junit.Test;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author Damon S.
 */
public class IdFactoryTester {

    @Test
    public void testNextId() {
        System.out.println("<<<<<<<<<雪花ID[WorkerId = 0]: " + IdFactory.instance().nextId());
        System.out.println("<<<<<<<<<雪花ID[WorkerId = 2]: " + IdFactory.instance().nextId(IdFactoryTester.class));
        System.out.println("<<<<<<<<<UUID[WorkerId = null]: " + IdFactory.instance().nextUID());
    }

    @Test
    public void testConcurrentNextId() {
        Runnable test =
                () -> System.out.println(Thread.currentThread().getName() + ": " +
                    IdFactory.instance().nextId(IdFactoryTester.class));

        int loop = 10;
        while (loop-- > 0) {
            new Thread(test).start();
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
