package com.yzkj.starter.application.command;

import com.yzkj.framework.command.BaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Tag(name = "用户添加", description = "用于用户添加的对象")
public class UserAddCommand extends BaseCommand {

    @Schema(description = "用户名，不能为空", example = "xxx", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户名不能为空")
    private String userName;
}
