package com.damon.media.domain.slot.layout.aggregate;

import com.damon.media.domain.slot.layout.entity.FeedLayoutEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 信息流布局样式ID
 * @author Damon S.
 */
public class LayoutId extends AbstractId<LayoutId, Long> {
    private static final long serialVersionUID = 5212690159857076L;

    public LayoutId() {
        super(IdFactory.instance().nextId(FeedLayoutEntry.class));
    }

    public LayoutId(Long identifier) {
        super(identifier);
    }
}
