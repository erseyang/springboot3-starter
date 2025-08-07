package com.yzkj.framework.mvc.result.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Tag(name="可排序查询参数对象", description = "如果页面需要排序，用此方法")
public abstract class BasePageOrder extends BasePage {

    @Schema(description = "排序对象列表", example = "20", defaultValue = "20")
    private List<SortItem> pageSorts;

    public void defaultSort(SortItem sortItem) {
        this.defaultPageSorts(Arrays.asList(sortItem));
    }

    public void defaultPageSorts(List<SortItem> sorts) {
        if (CollectionUtils.isEmpty(sorts)) {
            return;
        }
        this.pageSorts = sorts;
    }
}
