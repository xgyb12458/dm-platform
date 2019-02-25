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

    /**货号*/
    @Column(name = "spu_code")
    private String spuCode;

    /**商品名称*/
    @Column private String name;

    /**商品副标题*/
    @Column(name = "sub_title")
    private String subTitle;

    /**主图*/
    @Column(name = "image_id")
    private Long    imageId;

    /**商品图片Id集合*/
    @Column(name = "album_images")
    private String albumImages;

    /**商品描述*/
    @Column private String description;

    /**商品价格*/
    @Column private Long price;

    /**商品市场价*/
    @Column(name = "market_price")
    private Long marketPrice;

    /**审核状态*/
    @Column(name = "verify_state")
    private String verifyState;

    /**商品状态：草稿，待发布，已上架，已下架*/
    @Column(name = "state")
    private String state;

    /**售罄标识*/
    @Column(name = "sold_out")
    private String soldOut;

    /**新品标识*/
    @Column(name = "new_product")
    private String newProduct;

    /**推荐标识*/
    @Column(name = "recommended")
    private String recommended;

    /**商品重量，单位：毫克*/
    @Column private Long weight;

    /**是否已删除*/
    @Column private String removed;

    /**商品库存*/
    @Column private Integer inventory;

    /**安全库存预警值*/
    @Column(name = "safety_stock")
    private Integer safetyStock;

    /**型号*/
    @Column private String model;

    /**商品类型*/
    @Column private String type;

    /**运费模板*/
    @Column(name = "freight_template_id")
    private Long freightTemplateId;

    /**是否可退货*/
    @Column(name = "can_return")
    private String canReturn;

    /**商品类别*/
    @Column(name = "category_id")
    private Long categoryId;

    /**商品品牌*/
    @Column(name = "brand_id")
    private Long brandId;

    /**发货仓库*/
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**供应商*/
    @Column(name = "supplier_id")
    private Long supplierId;

    /**商品详情*/
    @Column(name = "h5_detail")
    private String h5Detail;

    /**销量*/
    @Column(name = "sold_volume")
    private Integer soldVolume;

    /**配送区域*/
    @Column(name = "delivery_region")
    private String deliveryRegion;

    /**创建人*/
    @Column(name = "created_by")
    private Long createdBy;
    /**最近修改人*/
    @Column(name = "updated_by")
    private Long updatedBy;
    /**创建时间*/
    @Column(name = "created_at")
    private Timestamp createdAt;
    /**最近一次修改时间*/
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
