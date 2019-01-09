package com.damon.bank.domain.payment.aggregate;

import com.damon.bank.domain.payment.entity.PaymentEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 订单支付唯一标识
 * @author Damon S.
 */
public final class PaymentId extends AbstractId<PaymentId, Long> {
    private static final long serialVersionUID = 1L;

    public PaymentId() {
        super(IdFactory.instance().nextId(PaymentEntry.class));
    }

    public PaymentId(Long identifier) {
        super(identifier);
    }
}
