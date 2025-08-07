package com.yzkj.starter.controller;

import com.yzkj.framework.annotation.Security;
import com.yzkj.framework.mvc.controller.BaseController;
import com.yzkj.framework.mvc.result.ApiResult;
import com.yzkj.starter.application.DeptApplicationService;
import com.yzkj.starter.application.UserApplicationService;
import com.yzkj.starter.application.command.UserAddCommand;
import com.yzkj.starter.application.model.UserItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user")
@Tag(name = "用户接口", description = "用于与用户相关的接口")
public class UserController extends BaseController {

    @Resource
    private DeptApplicationService deptApplicationService;

    @Resource
    private UserApplicationService userApplicationService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @Operation(summary = "分期信息添加,添加token，便于与旧系统进行接口交互", description = "分期的添加")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "响应成功", content = @Content(mediaType = "application/json",
                    schema = @Schema(description = "ApiResult中的data对象就为UserVo", anyOf = {ApiResult.class}
                    ))),
            @ApiResponse(responseCode = "500", description = "错误")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "参数", content = @Content(schema = @Schema(implementation = UserAddCommand.class)))
    @Security(checkLogon = true)
    public ApiResult<Void> add(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("userToken") String token, @Valid @RequestBody UserAddCommand command){
        return ApiResult.ok();
    }

    @RequestMapping("/dept/{deptId}")
    public String queryDept(@PathVariable("deptId") String deptId) {
        return deptApplicationService.queryDeptName(deptId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ApiResult<UserItemDto> user(HttpServletRequest request, HttpServletResponse response, @PathVariable("userId") String userId) {
        UserItemDto dto = userApplicationService.queryUserItemById(userId);
        return ApiResult.ok(dto);
    }
}
