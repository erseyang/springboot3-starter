package com.yzkj.framework.command;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询基础类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseListCommand extends BaseCommand {

    /***
     * 默认的数据大小
     */
    private static final Long DEFAULT_PAGE_SIZE = 20L;

    @Schema(name = "当前页", description = "当前页", example = "1")
    private Long pageNo;

    @Schema(name = "当前页数据", description = "当前页", example = "20")
    private Long pageSize;

    public void setPageNo(Long pageNo) {
        if (pageNo != null && pageNo <= 0) {
            this.pageNo = 1L;
        } else {
            this.pageNo = pageNo;
        }
    }

    public void setPageSize(Long pageSize) {
        if (pageSize != null && pageSize <= 0) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
