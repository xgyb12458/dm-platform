package com.damon.bank.domain.trade.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * 交易数据库仓库
 * @author Damon S.
 */
@Repository
public interface TradeEntryRepository extends EntryRepository<TradeEntry, Long> {
}
