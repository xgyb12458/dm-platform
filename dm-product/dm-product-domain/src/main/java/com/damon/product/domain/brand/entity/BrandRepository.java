package com.damon.product.domain.brand.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface BrandRepository extends EntryRepository<BrandEntry, Long> {
}
