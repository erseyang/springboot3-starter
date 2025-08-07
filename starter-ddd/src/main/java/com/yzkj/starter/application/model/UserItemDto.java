package com.yzkj.starter.application.model;

import com.yzkj.framework.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Tag(name="用户", description = "银行回款统计列表")
public class UserItemDto extends BaseDTO {

    @Schema(name = "用户ID", description = "用户ID", example = "xxxjjj")
    private String userId;

    @Schema(name = "用户名称", description = "用户ID", example = "xxxjjj")
    private String userName;
}
