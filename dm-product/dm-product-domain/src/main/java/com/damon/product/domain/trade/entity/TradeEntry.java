package com.damon.product.domain.trade.entity;

import com.damon.shared.common.WorkerId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 主订单(一次交易)
 * @author Damon S.
 */
@Entity
@Data
@Builder
@WorkerId(9)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dm_order_trade")
public class TradeEntry {
    /**
     * 基础信息：包含订单号，订单时间，订单状态等信息；
     * 商品信息：包含商品价格，商品名称，商品链接等信息；
     * 支付方式：包含支付方式，支付状态，支付时间，支付单号等信息；
     * 配送方式：是否包邮，不包邮时配送公司是什么；
     * 权益资产：包含红包，卡券，积分，京豆等虚拟资产；
     * 发票信息：发票类型选择，增值税普通发票还是增值税专用发票，电子发票还是不开票等信息；
     * 物流信息：物流具体的状态，什么时间点，哪个站点/仓，哪位配送员进行配送，是否签收，节点时间等信息。
     *
     * 支付信息：涉及支付的字段信息，主要包括支付方式、支付金额、订单金额、优惠金额等；
     * 促销信息：涉及促销的字段信息，主要包括优惠方式、优惠面额、折扣等；
     * 商品信息：涉及订单中的商品字段，主要包括商品名称、单价、数量、所属店铺等；
     * 时间信息：涉及订单流转中各个时间戳的字段，包括下单时间、支付时间、发货时间、完成时间等
     * 状态信息：涉及订单流转中状态变更的字段，主要包括订单状态、物流状态及退款状态等；
     * 用户信息：涉及用户的信息，比如买家姓名、注册手机号、收件人等信息；
     * 配送信息：涉及订单配送的基本信息，比如配送方式、物流单号等；
     *
     * 商品信息需要从商品获取，促销信息从促销系统获取，库存从库存系统获取，
     * 支付信息从支付系统获取，发票从发票系统，业务表示从对应的业务系统获取.
     *
     * 生成订单后，还要进行订单拆分，包含优惠拆分和订单拆分，紧接着进入wms系统，最后走财务开票了流程。
     *
     */

    @Id
    @NotNull
    @Column private Long tradeId;
    @Column private String orderState;
    @Column private String orderType;
    @Column private String payChannel;
    @Column private String payState;

    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createdAt;
    @Column private Timestamp updatedAt;

}
