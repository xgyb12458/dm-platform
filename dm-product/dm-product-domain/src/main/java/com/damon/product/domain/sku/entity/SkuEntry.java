package com.damon.product.domain.sku.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "dm_sku")
public class SkuEntry {
    @Id
    @NonNull
    @Column private Long        skuId;
    @Column private Long        productId;
    @Column private String      skuCode;
    @Column private String      name;
    @Column private String      image;
    @Column private String      state;
    @Column private Integer     inventory;
    @Column private Integer     secureInventory;
    @Column private Long        price;
    @Column private Long        reduction;
    @Column private Long        promoteFee;
    @Column private Long        exchangePrice;
    @Column private Long        serviceFee;
    @Column private Long        exchangePoint;
    @Column private Long        netWorth;
    @Column private String      barCode;
    @Column private Long        createdBy;
    @Column private Long        updatedBy;
    @Column private Timestamp   createdAt;
    @Column private Timestamp   updatedAt;
}
