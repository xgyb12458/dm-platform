package com.damon.media.domain.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

public class PerformanceTest {

    @Test
    public void compareStreamAndForLoop() {
        //p1表示for性能,p2表示流处理性能
        long p1 = 0, p2 = 0;
        int n = 500000;
        ArrayList<Integer> arr = Lists.newArrayList();
        for(int j = 0; j < 100; j ++) {
            for(int i = 0; i < n; i ++) {
                arr.add(i);
            }

            Integer sum = 0;
            long t1 = System.nanoTime();
            for(Integer v : arr) {
                sum = sum + v;
            }
            long t2 = System.nanoTime();
//            arr.stream().reduce(0, (a, b) -> a + b);
            arr.stream().parallel().reduce(0, (a, b) -> a + b);
            long t3 = System.nanoTime();
            p1 += (t2 - t1);
            p2 += (t3 - t2);
            arr.clear();
        }
        System.out.println(p1 / 100 / 1000);
        System.out.println(p2 / 100 / 1000);

        
    }
}
