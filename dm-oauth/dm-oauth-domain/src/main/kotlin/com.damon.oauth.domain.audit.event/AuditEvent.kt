package com.damon.product.domain.brand.event

import org.springframework.context.ApplicationEvent

/**
 * 品牌信息变革事件
 * @author Damon S.
 * @date 2019年02月24日 17:07
 * @version v1.0.1
 */
class AuditEvent(source : Any) : ApplicationEvent(source)


class OperateEvent(source : Any) : ApplicationEvent(source)


class LoginEvent(source : Any) : ApplicationEvent(source)

