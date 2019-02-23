package com.damon.product.core.query.handler.spu;

import com.damon.product.api.dto.req.spu.CreateSpuReqDTO;
import com.damon.product.domain.sku.aggregate.ProductSku;
import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.domain.spu.command.CreateSpuCommand;
import com.damon.product.domain.spu.event.SpuCreatedEvent;
import com.damon.product.shared.enums.SpuState;
import com.damon.product.shared.enums.VerifyState;
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
    public static CreateSpuCommand transformCommand(CreateSpuReqDTO req) {
        Long currentUserId = 10000L;

        List<ProductSku> productSkus = req.getSkus().stream().map(
                item -> ProductSku.builder()
                        .specIds(item.getSpecIds())
                        .skuCode(item.getSkuCode())
                        .name(item.getName())
                        .images(item.getImages())
                        .inventory(item.getInventory())
                        .safetyStock(item.getSafetyStock())
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
//                .imageId(req.getImageId())
                .skus(productSkus)
                .verifyState(VerifyState.DRAFTING)
                .spuState(SpuState.DRAFT)
                .removed(YesNoEnum.NO)
                .description(req.getDescription())
                .price(req.getPrice())
                .inventory(req.getInventory())
                .model(req.getModel())
                .type(req.getType())
//                .canReturn(req.getCanReturn())
                .categoryId(req.getCategoryId())
                .brandId(req.getBrandId())
                .warehouseId(req.getWarehouseId())
                .supplierId(req.getSupplierId())
                .h5Detail(req.getH5Detail())
                .deliveryRegion(req.getDeliveryRegion())
                .weight(req.getWeight())
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
    static boolean validate(SpuCreatedEvent event) {

        return true;
    }
}
