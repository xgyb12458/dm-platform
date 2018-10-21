package com.damon.oauth.domain.user.aggregate;

import com.damon.shared.model.AbstractIdentifier;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public class UserId extends AbstractIdentifier<UserId, Long> {
    private static final long serialVersionUID = 1L;

    public UserId(Long identifier) {
        super(identifier);
    }
}
