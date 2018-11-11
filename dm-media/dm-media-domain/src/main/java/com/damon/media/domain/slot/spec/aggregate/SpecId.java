package com.damon.media.domain.slot.spec.aggregate;

import com.damon.media.domain.slot.spec.entity.SlotSpecEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public class SpecId extends AbstractId<SpecId, Long> {
    private static final long serialVersionUID = 6901596757076L;

    public SpecId() {
        super(IdFactory.instance().nextId(SlotSpecEntry.class));
    }

    public SpecId(Long identifier) {
        super(identifier);
    }
}
