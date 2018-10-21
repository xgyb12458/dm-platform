package com.damon.media.domain.slot.layout;

import com.damon.shared.common.Specification;
import com.damon.media.shared.enums.LayoutType;
import org.springframework.util.ObjectUtils;

/***
 * 信息流布局样式
 * @author Damon S.
 */
public interface FeedLayout<T extends FeedLayout<T>> extends Specification<T> {
    /***
     * 样式ID
     * @return ID值
     */
    Long getLayoutId();

    /***
     * 图片宽度
     * @return 像素值
     */
    Integer getImageWidth();

    /***
     * 图片高度
     * @return 像素值
     */
    Integer getImageHeight();

    /***
     * 图片个数
     * @return 个数
     */
    Integer getImageCount();

    /***
     * 信息流样式示意图
     * @return 示意图地址
     */
    String getSnapshot();

    /***
     * 信息流样式
     * @return 样式类型
     */
    LayoutType getLayoutType();


    /***
     * 比较两个值对象是否相同
     * @param o 比较对象
     * @return 返回是否相同
     */
    @Override
    default boolean sameAs(T o) {
        return this == o
                || !ObjectUtils.isEmpty(o)
                && getClass() == o.getClass()
                && getLayoutId().equals(o.getLayoutId());
    }
}
