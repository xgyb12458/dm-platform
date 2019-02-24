package com.damon.product.domain.category.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 商品类别
 * @author Damon S.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pms_spu_category")
public class CategoryEntry {
    @Id
    @NonNull
    @Column(name = "category_id")
    private Long                categoryId;
    @Column private String      name;
    @Column private String      code;
    @Column private Long        parentId;

    @Column(name = "created_by")
    private Long                createdBy;
    @Column(name = "updated_by")
    private Long                updatedBy;
    @Column(name = "created_at")
    private Timestamp           createdAt;
    @Column(name = "updated_at")
    private Timestamp           updatedAt;
}
