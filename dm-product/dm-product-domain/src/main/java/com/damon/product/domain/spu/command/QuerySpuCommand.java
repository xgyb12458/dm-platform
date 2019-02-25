package com.damon.product.domain.spu.command;

import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.SpuState;
import com.damon.product.shared.enums.VerifyState;
import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

/**
 * SPU查询命令
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月25日 09:36
 */
@Value
@Builder
public class QuerySpuCommand {
    private final String            spuCode;
    private final String            name;
    private final VerifyState       verifyState;
    private final YesNoEnum         newProduct;
    private final YesNoEnum         recommended;
    private final YesNoEnum         soldOut;
    private final YesNoEnum         supportReturn;
    private final SpuState          state;
    private final String            model;
    private final ProductType       type;
    private final Long              categoryId;
    private final Long              brandId;
    private final Long              warehouseId;
    private final Long              supplierId;
    private final String            deliveryRegion;
    private final Long              createdBy;
    private final Instant           createdFrom;
    private final Instant           createdTo;
}
