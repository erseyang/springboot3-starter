package com.yzkj.dubbo.dept;

import com.yzkj.dubbo.BaseDubboService;

public interface DeptDubboService extends BaseDubboService {

    public String queryDeptName(String deptId);
}
