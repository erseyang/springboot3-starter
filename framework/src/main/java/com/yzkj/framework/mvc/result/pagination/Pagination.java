package com.yzkj.framework.mvc.result.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name="返回的列表结果", description = "返回的结果列表")
public class Pagination<T> implements java.io.Serializable {

    @Schema(description = "返回的列表对象", example = "[{xx:xx},{aa:aa}]")
    private List<T> records;

    @Schema(description = "返回的页面总数", example = "20")
    private Long total;

    @Schema(description = "返回的当前页", example = "20")
    private Long currentPage;

    @Schema(description = "返回的页面条数", example = "20", defaultValue = "20L")
    private Long pageSize;
}
