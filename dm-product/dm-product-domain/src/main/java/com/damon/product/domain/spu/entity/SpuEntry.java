package com.damon.product.domain.spu.entity;

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
 * SPU表，主商品表
 * @author Damon S.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dm_spu")
public class SpuEntry {
    @Id
    @NotNull
    @Column private Long        spuId;
    @Column private String      spuCode;
    @Column private String      name;
    @Column private String      image;
    @Column private String      desc;
    @Column private Long        price;
    @Column private String      reviewState;
    @Column private String      productState;
    @Column private Integer     removed;
    @Column private Integer     inventory;
    @Column private String      model;
    @Column private String      type;
    @Column private Integer     canReturn;
    @Column private Long        categoryId;
    @Column private Long        brandId;
    @Column private Long        warehouseId;
    @Column private Long        supplierId;
    @Column private String      h5Detail;
    @Column private Integer     saleVolume;
    @Column private String      deliverRegion;
    @Column private Long        length;
    @Column private Long        width;
    @Column private Long        height;
    @Column private Long        weight;
    @Column private Long        boxNum;
    @Column private Long        createdBy;
    @Column private Long        updatedBy;
    @Column private Timestamp   createdAt;
    @Column private Timestamp   updatedAt;
}
