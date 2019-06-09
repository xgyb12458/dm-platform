package com.damon.shared.id.impl;

import com.damon.shared.common.Constants;
import com.damon.shared.exception.SystemException;
import com.damon.shared.utils.ApplicationUtils;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 抽象ID工厂，获取workerId
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月18日 00:04
 */
abstract class AbstractIdFactory {
    int initWorkerId = -1;

    private static final Integer DEFAULT_WORKER_ID = 0;
    private static final Integer VAL_MOD = 256;

    private static final String WORKER_ID_PREFIX = "ID_WORKER:";
    private static final String MSG =
            Constants.PREFIX_SHARED + "==获取服务器本地MAC失败，初始化ID生成器失败== {0}";

    private static final Logger log = LoggerFactory.getLogger(AbstractIdFactory.class);

    int findWorkerId() {
        try {
            String hostMacAddress = ApplicationUtils.findLocalMac();

            if (Strings.isNullOrEmpty(hostMacAddress)) {
                // 获取当前MAC注册的 WorderID 值
                initWorkerId = findRegisteredWorkerId(hostMacAddress);
            }
        } catch (UnknownHostException | SocketException e) {
            initWorkerId = -1;
            log.error(MSG, e);
            throw new SystemException(MSG);
        }
        return initWorkerId;
    }

    private int findRegisteredWorkerId(String macKey) {
        // 从redis中获取WorkerId
        int redisWorkerId = findRedisWorkerId(macKey);
        // 若未取到 Id 值，则根据 MAC 地址生成，并注册到 REDIS
        if (ObjectUtils.isEmpty(redisWorkerId) ||
                redisWorkerId <= DEFAULT_WORKER_ID) {
            redisWorkerId = createNewWorkerId(macKey);
        }
        return redisWorkerId;
    }

    private int createNewWorkerId(String macKey) {
        int redisWorkerId = Math.abs(macKey.hashCode() % VAL_MOD);
        // 注册这个 Id 值，若已被占用，则自动加 1 重试
        while (!registerWorkerId(macKey, redisWorkerId)) {
            ++redisWorkerId;
        }
        return redisWorkerId;
    }

    private int findRedisWorkerId(String macKey) {
        String workerIdKey = buildWorkerIdKey(macKey);
        return 23;
    }

    /** 注册成功返回 true */
    private boolean registerWorkerId(String macKey, int workerId) {
        String workerIdKey = buildWorkerIdKey(macKey);
        return workerId > 0;
    }

    /** 新建workerId key */
    private String buildWorkerIdKey(String macKey) {
        return WORKER_ID_PREFIX + StringUtils.replace(
                macKey,
                Constants.STR_CENTER_LINE,
                Constants.STR_EMPTY);
    }
}
