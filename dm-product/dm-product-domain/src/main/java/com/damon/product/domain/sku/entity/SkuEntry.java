package com.damon.product.domain.sku.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * SKU单品
 * @author Damon S.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pms_sku")
public class SkuEntry {
    @Id
    @NotNull
    @Column(name = "sku_id")
    private Long        skuId;
    @Column(name = "product_id")
    private Long        productId;
    @Column(name = "sku_code")
    private String      skuCode;
    @Column
    private String      name;
    @Column
    private String      image;
    @Column
    private String      state;
    @Column
    private Integer     inventory;
    @Column(name = "safety_stock")
    private Integer     safetyStock;
    @Column
    private Long        price;
    @Column
    private Long        reduction;
    @Column(name = "promote_fee")
    private Long        promoteFee;
    @Column(name = "exchange_price")
    private Long        exchangePrice;
    @Column(name = "service_fee")
    private Long        serviceFee;
    @Column(name = "exchange_point")
    private Long        exchangePoint;
    @Column(name = "net_worth")
    private Long        netWorth;
    @Column(name = "bar_code")
    private String      barCode;
    @Column(name = "created_by")
    private Long        createdBy;
    @Column(name = "updated_by")
    private Long        updatedBy;
    @Column(name = "created_at")
    private Timestamp   createdAt;
    @Column(name = "updated_at")
    private Timestamp   updatedAt;
}
