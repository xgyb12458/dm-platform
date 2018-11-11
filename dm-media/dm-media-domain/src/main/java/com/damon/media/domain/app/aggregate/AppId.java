package com.damon.media.domain.app.aggregate;

import com.damon.media.domain.app.entity.AppEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 媒体ID
 * @author Damon S.
 */
public final class AppId extends AbstractId<AppId, Long> {
    private static final long serialVersionUID = 9615900157476L;

    public AppId() {
        super(IdFactory.instance().nextId(AppEntry.class));
    }

    public AppId(Long identifier) {
        super(identifier);
    }
}
