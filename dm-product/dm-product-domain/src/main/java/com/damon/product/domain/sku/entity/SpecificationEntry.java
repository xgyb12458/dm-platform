package com.damon.product.domain.sku.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * SKU规格
 * @author Damon S.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pms_sku_specification")
public class SpecificationEntry {
    @Id
    @NonNull
    @Column(name = "spec_id") private Long        specId;
    @Column private String      name;
    @Column private String      value;
    @Column private String      type;
    @Column private Integer     sort;
    @Column(name = "parent_id") private Long        parentId;
    @Column(name = "created_by") private Long        createdBy;
    @Column(name = "updated_by") private Long        updatedBy;
    @Column(name = "created_at") private Timestamp   createdAt;
    @Column(name = "updated_at") private Timestamp   updatedAt;
}
