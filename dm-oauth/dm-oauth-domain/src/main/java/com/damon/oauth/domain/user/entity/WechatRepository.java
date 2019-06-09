package com.damon.oauth.domain.user.entity;

import com.damon.shared.model.EntryRepository;
import org.springframework.stereotype.Repository;

/**
 * 微信信息数据处理仓库
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月10日 22:19
 */
@Repository
public interface WechatRepository extends EntryRepository<WechatEntry, Long> {
}
