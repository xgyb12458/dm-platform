package com.damon.product.domain.sku.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SKU单品关联
 * @author Damon S.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pms_sku_spec_relate")
public class SkuSpecRelateEntry {
    @Id
    @NonNull
    @Column(name = "sku_id")
    private Long        skuId;
    @Column(name = "spec_id")
    private Long        specId;
    @Column(name = "sort")
    private Integer     sort;
}
