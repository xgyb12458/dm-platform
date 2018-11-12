package com.damon.shared.common;

import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 唯一标识生成器
 * @author Damon S.
 */
public final class IdFactory {
    private static final Integer DEFAULT_WORKER_ID = 0;

    private IdWorker workerNick = new IdWorker(DEFAULT_WORKER_ID);
    private Set<Integer> workerIds = new HashSet<>();
    private Map<String, IdWorker> workers = new ConcurrentHashMap<>();

    private static final IdFactory FACTORY = new IdFactory();

    private final static Logger LOGGER =
            LoggerFactory.getLogger(IdFactory.class);

    public static IdFactory instance() {
        return FACTORY;
    }

    /***
     * 获取UUID值
     */
    public String nextUID() {
        return UUID.randomUUID().toString().replace(
                Constants.UUID_DELIMITER,
                Constants.EMPTY
        );
    }

    /***
     * 获取以Long为值的ID
     */
    public Long nextId(Class clazz) {
        if (DEFAULT_WORKER_ID == findWorkerId(clazz)) {
            return this.nextId();
        }
        return nextId(clazz.getName(), findWorkerId(clazz));
    }

    public Long nextId() {
        return workerNick.nextId();
    }

    private Long nextId(String clazzName, int workerId) {
        IdWorker worker = workers.get(clazzName);

        if (null == worker) { synchronized (this) {
            worker = workers.get(clazzName);

            if (null == worker) {
                worker = new IdWorker(workerId);
                workers.put(clazzName, worker);
                workerIds.add(workerId);
            }
        }}
        return worker.nextId();
    }

    private int findWorkerId(Class clazz) {
        WorkerId workerId = (WorkerId) clazz.getAnnotation(WorkerId.class);
        if (Objects.isNull(workerId)) {
            LOGGER.warn("指定类型[" + clazz.getName() + "]未注解@WorkerId");
            return DEFAULT_WORKER_ID;
        }
        if (workerId.value() > workerNick.getMaxWorkerId() || 1 > workerId.value()) {
            LOGGER.error("类型[" + clazz.getName() + "]的WorkerId注解值超出范围(0, " + workerNick.getMaxWorkerId() + "]");
            throw new SystemException(ResponseCodeEnum.INTERNAL_ERROR.getCode(), "生成唯一标识异常");
        }
        if (!workers.containsKey(clazz.getName()) && workerIds.contains(workerId.value())) {
            LOGGER.error("注解WorkerId{" + workerId.value() + "}值已被占用");
            throw new SystemException(ResponseCodeEnum.INTERNAL_ERROR.getCode(), "生成唯一标识异常");
        }
        return workerId.value();
    }
}
