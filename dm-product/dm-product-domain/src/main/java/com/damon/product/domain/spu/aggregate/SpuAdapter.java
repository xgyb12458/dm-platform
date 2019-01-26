package com.damon.product.domain.spu.aggregate;

import com.damon.product.api.dto.req.spu.CreateProductReqDTO;
import com.damon.product.domain.sku.aggregate.ProductSku;
import com.damon.product.domain.spu.command.CreateSpuCommand;
import com.damon.product.domain.spu.event.ProductCreatedEvent;
import com.damon.product.shared.enums.ProductState;
import com.damon.product.shared.enums.ReviewState;
import com.damon.shared.enums.YesNoEnum;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SPU验证
 * @author Damon S.
 */
public class SpuAdapter {

    /**
     * 转换对象
     */
    public static CreateSpuCommand transformCommand(CreateProductReqDTO req) {
        Long currentUserId = 10000L;

        List<ProductSku> productSkus = req.getSkus().stream().map(
                item -> ProductSku.builder()
                        .specIds(item.getSpecIds())
                        .skuCode(item.getSkuCode())
                        .name(item.getName())
                        .images(item.getImages())
                        .inventory(item.getInventory())
                        .secureInventory(item.getSecureInventory())
                        .price(item.getPrice())
                        .reduction(item.getReduction())
                        .promoteFee(item.getPromoteFee())
                        .serviceFee(item.getServiceFee())
                        .exchangePrice(item.getExchangePrice())
                        .exchangePoint(item.getExchangePoint())
                        .netWorth(item.getNetWorth())
                        .barCode(item.getBarCode())
                        .createdBy(currentUserId)
                        .build()
        ).collect(Collectors.toList());

        return CreateSpuCommand.builder()
                .spuId(new SpuId())
                .spuCode(req.getSpuCode())
                .name(req.getName())
                .images(req.getImages())
                .skus(productSkus)
                .reviewState(ReviewState.DRAFTING)
                .productState(ProductState.IN_DRAFT)
                .removed(YesNoEnum.NO)
                .desc(req.getDesc())
                .price(req.getPrice())
                .inventory(req.getInventory())
                .model(req.getModel())
                .type(req.getType())
                .canReturn(req.getCanReturn())
                .categoryId(req.getCategoryId())
                .brandId(req.getBrandId())
                .warehouseId(req.getWarehouseId())
                .supplierId(req.getSupplierId())
                .h5Detail(req.getH5Detail())
                .deliveryRegion(req.getDeliveryRegion())
                .length(req.getLength())
                .width(req.getWidth())
                .height(req.getHeight())
                .weight(req.getWeight())
                .boxNum(req.getBoxNum())
                .createdBy(currentUserId)
                .build();
    }

    /**
     * 验证创建商品命令参数是否合法
     */
    static boolean validate(CreateSpuCommand command) {

        return true;
    }

    /**
     * 验证创建商品事件参数是否合法
     */
    static boolean validate(ProductCreatedEvent event) {

        return true;
    }
}
