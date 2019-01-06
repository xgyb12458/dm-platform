package com.damon.order.domain.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 主订单(一次交易)
 * @author Damon S.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dm_order_payment")
public class PaymentEntry {

    @Id
    @NotNull
    @Column
    private Long paymentId;
}
