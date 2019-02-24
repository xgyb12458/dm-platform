package com.damon.product.domain.category.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface CategoryRepository extends EntryRepository<CategoryEntry, Long> {
}
