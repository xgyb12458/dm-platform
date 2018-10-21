package com.damon.media.domain.slot.spec;

import com.damon.media.shared.enums.SlotType;
import com.damon.shared.common.Specification;
import com.damon.shared.enums.SwitchState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.ObjectUtils;

/***
 * 资源位规格基类
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public abstract class SlotSpecification<T extends SlotSpecification<T>>
        implements Specification<T> {
    @NonNull
    private final Long specId;
    private final Integer width;
    private final Integer height;
    private final String imageType;
    private final Integer imageSize;
    private final String snapshot;
    private final SwitchState state;


    /***
     * 获取资源位类型
     * @return 资源位类型枚举值
     */
    public abstract SlotType getType();


    @Override
    public boolean sameAs(T o) {
        return this == o
                || !ObjectUtils.isEmpty(o)
                && getClass() == o.getClass()
                && getSpecId().equals(o.getSpecId())
                && getHeight().equals(o.getHeight())
                && getWidth().equals(o.getWidth());
    }
}
