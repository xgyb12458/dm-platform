package com.damon.product.domain.sku.entity;

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
 * 销售SKU
 * @author Damon S.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pms_sku_sale")
public class SkuSaleEntry {
    @Id
    @NotNull
    @Column(name = "sale_id")
    private Long        saleId;
    @Column(name = "spu_id")
    private Long        spuId;
    @Column(name = "sku_id")
    private Long        skuId;
    @Column(name = "sale_sku_code")
    private String      saleSkuCode;
    @Column
    private String      state;
    @Column
    private Long        price;
    @Column
    private Long        reduction;
    @Column(name = "service_fee")
    private Long        serviceFee;
    @Column(name = "promote_fee")
    private Long        promoteFee;
    @Column(name = "exchange_point")
    private Long        exchangePoint;
    @Column(name = "net_worth")
    private Long        netWorth;
    @Column(name = "created_by")
    private Long        createdBy;
    @Column(name = "created_at")
    private Timestamp   createdAt;
    @Column(name = "updated_by")
    private Long        updatedBy;
    @Column(name = "updated_at")
    private Timestamp   updatedAt;
}
