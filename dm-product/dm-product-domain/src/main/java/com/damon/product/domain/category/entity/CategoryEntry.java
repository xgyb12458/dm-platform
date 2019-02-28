package com.damon.product.domain.category.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 商品类别（品类）
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
    private Long    categoryId;
    /**品类名称*/
    @Column private String  name;
    /**品类级别：0->1级；1->2级*/
    @Column private Integer level;
    /**商品数量*/
    @Column(name = "spu_count")
    private Integer spuCount;
    /**数量单位*/
    @Column(name = "spu_unit")
    private String spuUnit;
    /**是否显示在导航栏*/
    @Column(name = "nav_state")
    private String navState;
    /**显示状态*/
    @Column(name = "show_state")
    private String showState;
    @Column private Integer sort;
    @Column private String icon;
    @Column private String keywords;
    /**上级分类的编号：0表示一级分类*/
    @Column(name = "parent_id")
    private Long        parentId;
    @Column private String description;
    @Column(name = "created_by")
    private Long                createdBy;
    @Column(name = "updated_by")
    private Long                updatedBy;
    @Column(name = "created_at")
    private Timestamp           createdAt;
    @Column(name = "updated_at")
    private Timestamp           updatedAt;
}
