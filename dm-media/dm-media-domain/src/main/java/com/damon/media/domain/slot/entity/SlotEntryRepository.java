package com.damon.media.domain.slot.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon S.
 */
@Repository
public interface SlotEntryRepository extends EntryRepository<SlotEntry, Long> {
}
