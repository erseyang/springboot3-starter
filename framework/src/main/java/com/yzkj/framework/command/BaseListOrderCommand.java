package com.yzkj.framework.command;


import com.yzkj.framework.entity.PageSortEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 添加排序
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseListOrderCommand extends BaseListCommand {

    @Schema(name = "排序列表", description = "排序列表", example = "1")
    private List<PageSortEntity.Order> orders;
}
