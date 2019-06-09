package com.damon.shared.id.impl;

import com.damon.shared.id.BaseIdFactory;
import com.damon.shared.id.SnowflakeFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 雪花算法唯一标识生成器
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月17日 20:51
 */
public final class SnowflakeIdFactory extends AbstractIdFactory
        implements SnowflakeFactory, BaseIdFactory<Long> {

    private static final Integer DEFAULT_WORKER_ID = 0;

    private SnowflakeIdWorker workerNick = new SnowflakeIdWorker(DEFAULT_WORKER_ID);
    private Map<String, SnowflakeIdWorker> workers = new ConcurrentHashMap<>();
    private static final SnowflakeIdFactory FACTORY = new SnowflakeIdFactory();

    public static SnowflakeIdFactory instance() {
        return FACTORY;
    }


    /***
     * 生成默认ID
     * */
    @Override
    public synchronized Long nextId() {
        return workerNick.nextId();
    }

    /***
     * 本实现方式中不可用
     **/
    @Override
    public Long nextId(String prefix) {
        throw new UnsupportedOperationException();
    }

    /***
     * 获取以Long为值的ID
     * */
    @Override
    public synchronized Long nextId(Class clazz) {
        int workerId = initWorkerId;

        if (initWorkerId <= DEFAULT_WORKER_ID) {
            workerId = findWorkerId();
        }
        return nextId(clazz.getName(), workerId);
    }

    private Long nextId(String clazzName, int workerId) {
        SnowflakeIdWorker worker = workers.get(clazzName);

        if (Objects.isNull(worker)) {
            synchronized (this) {
            worker = workers.get(clazzName);

            if (Objects.isNull(worker)) {
                worker = new SnowflakeIdWorker(workerId);
                workers.put(clazzName, worker);
            }
        }}
        return worker.nextId();
    }
}
