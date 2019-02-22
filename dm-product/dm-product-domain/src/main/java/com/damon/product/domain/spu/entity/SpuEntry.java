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
@Table(name = "pms_spu")
public class SpuEntry {
    @Id
    @NotNull
    @Column(name = "spu_id")
    private Long spuId;
    @Column(name = "spu_code")
    private String spuCode;
    @Column private String name;
    @Column private String image;
    @Column private String desc;
    @Column private Long price;
    @Column(name = "review_state")
    private String reviewState;
    @Column(name = "spu_state")
    private String spuState;
    @Column private String removed;
    @Column private Integer inventory;
    @Column private String model;
    @Column private String type;
    @Column(name = "can_return")
    private String canReturn;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "warehouse_id")
    private Long warehouseId;
    @Column(name = "supplier_id")
    private Long supplierId;
    @Column(name = "h5_detail")
    private String h5Detail;
    @Column(name = "sold_volume")
    private Integer soldVolume;
    @Column(name = "delivery_region")
    private String deliveryRegion;
    @Column(name = "box_num")
    private Long boxNum;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "updated_by")
    private Long updatedBy;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
