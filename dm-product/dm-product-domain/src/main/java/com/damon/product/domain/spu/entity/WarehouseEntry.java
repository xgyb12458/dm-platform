package com.damon.product.domain.spu.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 商品仓库
 * @author Damon S.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pms_spu_warehouse")
public class WarehouseEntry {
    @Id
    @NonNull
    @Column(name = "warehouse_id") private Long      warehouseId;
    @Column private String      name;
    @Column private String      code;

    @Column(name = "created_by")
    private Long                createdBy;
    @Column(name = "updated_by")
    private Long                updatedBy;
    @Column(name = "created_at")
    private Timestamp           createdAt;
    @Column(name = "updated_at")
    private Timestamp           updatedAt;
}
