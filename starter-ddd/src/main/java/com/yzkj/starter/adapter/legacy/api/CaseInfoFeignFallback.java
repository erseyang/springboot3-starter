package com.yzkj.starter.adapter.legacy.api;

import com.yzkj.starter.adapter.legacy.dto.CaseReqInfoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * fegign接口降级工厂
 */
@Component
@Slf4j
public class CaseInfoFeignFallback implements FallbackFactory<CaseInfoFeignService> {


    @Override
    public CaseInfoFeignService create(Throwable cause) {
        return new CaseInfoFeignService() {

            @Override
            public JsonNode queryInfo(String token, CaseReqInfoDTO caseRequestInfo) {
                log.info("降级处理");
                return null;
            }
        };
    }
}
