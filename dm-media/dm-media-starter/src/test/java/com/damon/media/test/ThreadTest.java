package com.damon.media.app.test;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadTest {
    private ThreadLocal<Integer> localInt = ThreadLocal.withInitial(() -> 3);

    @Test
    public void testThreadLocalLogic() {
        for (int i = 0; i < 5; i++) {
            new ThreadDemo(i).start();
        }
        System.out.println("Main initial=" + localInt.get());
        localInt.set(5 + localInt.get());
        System.out.println("Main operate=" + localInt.get());
        localInt.remove();
    }

    @Test
    public void testJavaOperators() {
        System.out.println(3 >> 1);
        System.out.println(3 >> 2);
        System.out.println(3 << 1);
        System.out.println(3 << 2);
        System.out.println(-3 << 1);
        System.out.println(-3 << 2);
        System.out.println(-13 >>> 2);
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(-10));
        System.out.println(Long.toBinaryString(10));
        System.out.println(Long.toBinaryString(-10));
    }

    @Test
    public void testClassLoader() {
        System.out.println("Bootstrap ClassLoader loading================>>>");
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("ExtClassLoader loading=======================>>>");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("AppClassLoader loading=======================>>>");
        System.out.println(System.getProperty("java.class.path"));

        ClassLoader cl = ThreadDemo.class.getClassLoader();
        System.out.println("ThreadDemo ClassLoader=======================>>>");
        System.out.println("ThreadDemo ClassLoader is: " + cl.toString());
    }

    @Test
    public void testSerializer() {
        System.out.println(ChildStaticTest.x);
        System.out.println(ChildStaticTest.y);
        StaticTest.run();
    }

    @Test
    public void testBoundedThreadPool() {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                2,
                1L,
                TimeUnit.SECONDS,
                workQueue
        );
        for (int i = 1; i < 6; i++) {
            threadPool.execute(new ThreadDemo(i));
        }
        threadPool.shutdown();

    }

    @Test
    public void testUnboundedThreadPool() {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                5,
                10,
                1L,
                TimeUnit.SECONDS,
                workQueue
        );
        for (int i = 1; i <= 20; i++) {
            threadPool.execute(new ThreadDemo(i));
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程池中队列中的线程数量：" + workQueue.size());
        threadPool.shutdown();
    }

    class ThreadDemo extends Thread {
        private int value;

        ThreadDemo(int v) {
            this.value = v;
        }

        @Override
        public void run() {
            System.out.println("initial-"+value+": " + getName() + "=" + localInt.get());
            localInt.set(value + localInt.get());
            System.out.println("operate-"+value+": " + getName() + "=" + localInt.get());
            localInt.remove();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class StaticTest {
    static void run() {
        System.out.println(y);
    }
    static int x = 10;
    static {
        x=30;
        System.out.println("x=30");
    }
    static int y = x * 2;
}

class ChildStaticTest extends StaticTest {
    private static int m = 10;
    static {
        m=80;
        System.out.println("m=80");
    }
}
