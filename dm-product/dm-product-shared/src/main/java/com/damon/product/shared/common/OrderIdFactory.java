package com.damon.product.shared.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.UUID;

/**
 * 唯一标识生成器
 * @author Damon S.
 */
public final class OrderIdFactory {
    private static final String FORMAT_YEAR_SEC = "yyyyMMddhhmmss";

    public static String nextId() {
        DateFormat format = new SimpleDateFormat(FORMAT_YEAR_SEC);
        String rightNow = format.format(Instant.now());

        System.out.println(rightNow + System.nanoTime());

        int code = UUID.randomUUID().hashCode();
        System.out.println(code);

        return "";
    }
}
