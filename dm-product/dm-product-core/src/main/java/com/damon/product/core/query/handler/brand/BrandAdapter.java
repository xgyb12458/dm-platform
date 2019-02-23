package com.damon.product.core.query.handler.brand;

import com.damon.product.api.dto.req.brand.CreateBrandReqDTO;
import com.damon.product.domain.spu.command.CreateBrandCommand;

/**
 * 品牌管理功能适配器
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 18:04
 */
public class BrandAdapter {

    /**
     * 检查参数是否合法
     */
    public static Boolean checkParameter(CreateBrandReqDTO createBrandReqDTO) {

        return false;
    }

    /**
     * 转换成命令对象
     */
    public static CreateBrandCommand transformCommand(CreateBrandReqDTO createBrandReqDTO) {

        return null;
    }
}
