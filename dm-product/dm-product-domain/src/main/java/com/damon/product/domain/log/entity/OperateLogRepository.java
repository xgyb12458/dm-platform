package com.damon.product.domain.log.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月10日 20:50
 */
@Repository
public interface OperateLogRepository extends EntryRepository<OperateLogEntry, Long> {
}
