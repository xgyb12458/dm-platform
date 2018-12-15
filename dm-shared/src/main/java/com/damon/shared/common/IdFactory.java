package com.damon.shared.common;

import com.damon.shared.exception.SystemException;
import com.damon.shared.utils.ApplicationUtils;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 唯一标识生成器
 * @author Damon S.
 */
public final class IdFactory {
    private static final Integer DEFAULT_WORKER_ID = 0;
    private static final Integer VAL_MOD = 256;

    private IdWorker workerNick = new IdWorker(DEFAULT_WORKER_ID);
    private Map<String, IdWorker> workers = new ConcurrentHashMap<>();

    private static final IdFactory FACTORY = new IdFactory();
    public static IdFactory instance() {
        return FACTORY;
    }

    private static final String MSG = Constants.PREFIX_SHARED + "==获取服务器本地MAC失败，初始化ID生成器失败。";

    private final static Logger LOGGER =
            LoggerFactory.getLogger(IdFactory.class);

    private int initWorkerId = -1;

    /***
     * 获取UUID值
     * */
    public String nextUID() {
        return UUID.randomUUID().toString().replace(
                Constants.UUID_DELIMITER,
                Constants.STR_EMPTY
        );
    }

    /***
     * 默认ID
     * */
    public Long nextId() {
        return workerNick.nextId();
    }

    /***
     * 获取以Long为值的ID
     * */
    public Long nextId(Class clazz) {
        int workerId = initWorkerId;

        if (initWorkerId <= DEFAULT_WORKER_ID) {
            workerId = findWorkerId();
        }
        return nextId(clazz.getName(), workerId);
    }

    private Long nextId(String clazzName, int workerId) {
        IdWorker worker = workers.get(clazzName);

        if (Objects.isNull(worker)) {
            synchronized (this) {
            worker = workers.get(clazzName);

            if (Objects.isNull(worker)) {
                worker = new IdWorker(workerId);
                workers.put(clazzName, worker);
            }
        }}
        return worker.nextId();
    }

    private int findWorkerId() {
        try {
            String hostMacAddress = ApplicationUtils.findLocalMac();

            if (!Strings.isNullOrEmpty(hostMacAddress)) {
                // 获取当前IP注册的 WorderID 值
                initWorkerId = findRegisteredWorkerId(hostMacAddress);
            }
        } catch (UnknownHostException | SocketException e) {
            initWorkerId = -1;
            LOGGER.error(MSG + e.toString());
            throw new SystemException(MSG);
        }
        return initWorkerId;
    }

    private int findRegisteredWorkerId(String macKey) {
        // TODO: 从REDIS获取对应ID值
        int redisWorkerId = 2;
        // 若未取到 Id 值，则根据 MAC 地址生成，并注册到 REDIS
        if (ObjectUtils.isEmpty(redisWorkerId) ||
                redisWorkerId <= DEFAULT_WORKER_ID) {
            redisWorkerId = Math.abs(macKey.hashCode() % VAL_MOD);
            // 注册这个 Id 值，若已被占用，则自动加 1 重试
            while (!registerWorkerId(redisWorkerId)) {
                ++redisWorkerId;
            }
        }
        return redisWorkerId;
    }

    /** 注册成功返回 true */
    private boolean registerWorkerId(int workerId) {
        return workerId > 0;
    }
}
