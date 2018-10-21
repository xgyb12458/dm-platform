package com.damon.media.domain.slot.aggregate;

import com.damon.media.domain.slot.entity.SlotEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractIdentifier;


/***
 * 资源位ID
 * @author Damon S.
 */
public class SlotId extends AbstractIdentifier<SlotId, Long> {
    private static final long serialVersionUID = 2126959007576L;

    public SlotId() {
        super(IdFactory.instance().nextId(SlotEntry.class));
    }

    public SlotId(Long identifier) {
        super(identifier);
    }
}
