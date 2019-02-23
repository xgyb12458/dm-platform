package com.damon.product.domain.spu.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 商品品牌表
 * @author Damon S.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pms_spu_brand")
public class BrandEntry {
    @Id
    @NonNull
    @Column(name = "brand_id")
    private Long                brandId;
    @Column private String      name;
    @Column private String      code;
    /**品牌LOGO*/
    @Column private String      logo;
    /**是否已删除*/
    @Column private String      deleted;
    /**是否显示*/
    @Column private String      display;
    @Column private Integer     sort;
    /**首字母*/
    @Column(name = "first_letter")
    private String              firstLetter;
    /**是否为品牌制造商：0->不是；1->是*/
    @Column(name = "factory_state")
    private String              factoryState;
    /**专区大图*/
    @Column(name = "big_image")
    private String              bigImage;
    /**品牌故事*/
    @Column(name = "brand_story")
    private String              brandStory;

    @Column(name = "created_by")
    private Long                createdBy;
    @Column(name = "updated_by")
    private Long                updatedBy;
    @Column(name = "created_at")
    private Timestamp           createdAt;
    @Column(name = "updated_at")
    private Timestamp           updatedAt;
}
