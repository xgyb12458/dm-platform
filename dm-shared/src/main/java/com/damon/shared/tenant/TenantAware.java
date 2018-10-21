package com.damon.shared.tenant;

/**
 * 接口：获取租户唯一标识的能力
 * @author Damon S.
 * @param <ID> 唯一标识的数据类型
 */
public interface TenantAware<ID> {

    /**
     * 获取租户唯一标识
     * @return 返回唯一标识
     */
    ID getTenantId();
}
