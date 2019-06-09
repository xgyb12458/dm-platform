package com.damon.shared.id.impl;

import com.damon.shared.exception.SystemException;
import com.damon.shared.id.BaseIdFactory;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 生成时间戳主体的唯一ID标识。
 * 唯一标识ID的结构：
 *      1.前12位是当前年月日时分秒
 *      2.第13、14、15位为workerId
 *      3.最后4位序列数
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月17日 20:51
 */
public final class TimestampIdFactory extends AbstractIdFactory
        implements BaseIdFactory<String> {
    private long sequence = 0L;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private static final long SEQUENCE_MASK = ~(-1L << 12L);
    private static final String ID_PATTERN = "%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%2$03d%3$04d";
    private static final TimestampIdFactory FACTORY = new TimestampIdFactory();

    public static TimestampIdFactory instance() {
        return FACTORY;
    }

    /**
     * 无前缀时间戳
     * @return 时间戳ID
     */
    @Override
    public synchronized String nextId() {
        sequence = (sequence + 1) & SEQUENCE_MASK;
        return String.format(
                ID_PATTERN,
                LocalDateTime.now(),
                findWorkerId(),
                sequence).substring(2);
    }


    @Override
    public String nextId(String prefix) {
        if (StringUtils.isEmpty(prefix)) {
            throw new SystemException("唯一标识前缀不能为空");
        }
        return prefix + nextId();
    }
}
