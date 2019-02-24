package com.damon.product.domain.warehouse.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface WarehouseRepository extends EntryRepository<WarehouseEntry, Long> {
}
