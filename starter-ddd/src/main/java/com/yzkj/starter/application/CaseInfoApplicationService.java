package com.yzkj.starter.application;

import com.yzkj.framework.service.BaseService;

public interface CaseInfoApplicationService extends BaseService {

    public void checkCaseExist(String token, String caseId);
}
