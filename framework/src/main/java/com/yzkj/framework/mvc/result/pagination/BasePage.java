package com.yzkj.framework.mvc.result.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Tag(name="请求的分页参数", description = "")
public abstract class BasePage implements Serializable {

    @NotNull
    @Schema(description = "分页的页码", example = "1", defaultValue = "1")
    private Long pageNo;

    @NotNull
    @Schema(description = "分页的条数", example = "20", defaultValue = "20")
    private Long pageSize;

    public void setDefaultPage(Long pageNo) {
        if (pageNo == null || pageNo < 1) {
            this.pageNo = 1L;
        } else {
            this.pageNo = pageNo;
        }
    }

    public void setPageSize(Long pageSize) {
        if (pageSize == null || pageSize < 1) {
            this.pageSize = 20L;
        } else {
            this.pageSize = pageSize;
        }
    }
}
