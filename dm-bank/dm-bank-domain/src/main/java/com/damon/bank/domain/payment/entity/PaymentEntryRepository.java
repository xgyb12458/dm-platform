package com.damon.bank.domain.payment.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * 订单支付数据库仓库
 * @author Damon S.
 */
@Repository
public interface PaymentEntryRepository extends EntryRepository<PaymentEntry, Long> {
}
