package com.damon.product.domain.sku.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月14日 23:35
 */
@Repository
public interface SkuSaleRepository extends EntryRepository<SkuSaleEntry, Long> {
}
