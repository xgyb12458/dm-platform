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

    public Pagination(Integer pageIndex, Integer pageSize,
                      Integer totalRows, List<T> data) {
        this.data = data;
        this.pageInfo = new PageInfo(pageIndex, pageSize, totalRows);
    }

    @Value
    public static class PageInfo {
        /***当前页码*/
        private final Integer pageIndex;

        /***每页数量*/
        private final Integer pageSize;

        /***数据总数*/
        private final Integer totalRows;

        /***总页数*/
        public Integer getTotalPages() {
            return Double.valueOf(Math.ceil(totalRows / (pageSize * 1.0d))).intValue();
        }

        private PageInfo(Integer pageIndex, Integer pageSize, Integer totalRows) {
            this.totalRows = Optional.ofNullable(totalRows).orElse(Constants.INT_ZERO);
            this.pageSize = Optional.ofNullable(pageSize).orElse(Constants.DEFAULT_PAGE_SIZE);
            Integer totalPages = getTotalPages();
            this.pageIndex = pageIndex > totalPages ? totalPages : pageIndex;
        }
    }
}
