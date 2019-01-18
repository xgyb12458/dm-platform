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
@Table(name = "dm_sku_spec_relate")
public class SkuSpecRelateEntry {
    @Id
    @NonNull
    @Column private Long        skuId;
    @Column private Long        specId;
    @Column private Integer     sort;
}
