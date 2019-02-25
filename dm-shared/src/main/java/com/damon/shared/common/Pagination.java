package com.damon.shared.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.util.List;
import java.util.Optional;

/**
 * 分页处理
 * @author damon S. on 16/8/30.
 */
@Getter
@NoArgsConstructor
public final class Pagination<T> {
    /***分页信息*/
    private PageInfo pageInfo;

    @Setter
    private List<T> data;

    public Pagination(Long pageIndex, Long pageSize,
                      Long totalRows, List<T> data) {
        this.data = data;
        this.pageInfo = new PageInfo(pageIndex, pageSize, totalRows);
    }

    @Value
    public static class PageInfo {
        /***当前页码*/
        private final Long pageIndex;

        /***每页数量*/
        private final Long pageSize;

        /***数据总数*/
        private final Long totalRows;

        /***总页数*/
        public Long getTotalPages() {
            return Double.valueOf(Math.ceil(totalRows / (pageSize * 1.0d))).longValue();
        }

        private PageInfo(Long pageIndex, Long pageSize, Long totalRows) {
            this.totalRows = Optional.ofNullable(totalRows).orElse(Constants.LONG_ZERO);
            this.pageSize = Optional.ofNullable(pageSize).orElse(Constants.LONG_TEN);
            Long totalPages = getTotalPages();
            this.pageIndex = pageIndex > totalPages ? totalPages : pageIndex;
        }
    }
}
