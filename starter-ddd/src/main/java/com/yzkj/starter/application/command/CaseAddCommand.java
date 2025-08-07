package com.yzkj.starter.application.command;

import com.yzkj.framework.command.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class CaseAddCommand extends BaseCommand {
    @Override
    public String toString() {
        return "";
    }
}
