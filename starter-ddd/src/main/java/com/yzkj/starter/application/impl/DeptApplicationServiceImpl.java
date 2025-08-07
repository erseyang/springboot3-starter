package com.yzkj.starter.application.impl;

import com.yzkj.dubbo.dept.DeptDubboService;
import com.yzkj.starter.application.DeptApplicationService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class DeptApplicationServiceImpl implements DeptApplicationService {

    @DubboReference
    private DeptDubboService deptDubboService;

    @Override
    public String queryDeptName(String deptId) {
        return deptDubboService.queryDeptName(deptId);
    }
}
