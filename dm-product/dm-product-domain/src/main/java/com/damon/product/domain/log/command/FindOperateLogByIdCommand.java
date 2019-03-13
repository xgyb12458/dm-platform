package com.damon.product.domain.log.command;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 根据唯一标识获取日志数据
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月13日 23:32
 */
@Value
@RequiredArgsConstructor
public class FindOperateLogByIdCommand {
    private final Long logId;
}
