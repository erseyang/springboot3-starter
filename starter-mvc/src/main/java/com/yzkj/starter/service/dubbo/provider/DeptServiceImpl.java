package com.yzkj.starter.service.dubbo.provider;

import com.yzkj.dubbo.dept.DeptDubboService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class DeptServiceImpl implements DeptDubboService {

    @Override
    public String queryDeptName(String deptId) {
        return String.format("Yzkj Dept %s", deptId);
    }
}
