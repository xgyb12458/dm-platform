package com.damon.product.domain.trade.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface TradeEntryRepository extends EntryRepository<TradeEntry, Long> {
}
