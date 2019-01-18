package com.damon.product.domain.sku.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface SpecificationEntryRepository extends EntryRepository<SpecificationEntry, Long> {
}
