package com.damon.product.domain.supplier.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface SupplierRepository extends EntryRepository<SupplierEntry, Long> {
}
