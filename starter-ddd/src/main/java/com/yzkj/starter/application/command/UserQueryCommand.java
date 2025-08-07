package com.yzkj.starter.application.command;

import com.yzkj.framework.command.BaseCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Tag(name = "用户查询", description = "用于用户信息的查询")
public class UserQueryCommand extends BaseCommand {
    @Override
    public String toString() {
        return "";
    }
}
