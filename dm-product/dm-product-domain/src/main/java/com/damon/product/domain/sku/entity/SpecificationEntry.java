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
@Table(name = "dm_sku_specification")
public class SpecificationEntry {
    @Id
    @NonNull
    @Column private Long        specId;
    @Column private String      name;
    @Column private String      value;
    @Column private String      type;
    @Column private Integer     sort;
    @Column private Long        parentId;
    @Column private Long        createdBy;
    @Column private Long        updatedBy;
    @Column private Timestamp   createdAt;
    @Column private Timestamp   updatedAt;
}
