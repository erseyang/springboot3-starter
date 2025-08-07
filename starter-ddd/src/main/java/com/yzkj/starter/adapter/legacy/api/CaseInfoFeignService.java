package com.yzkj.starter.adapter.legacy.api;

import com.yzkj.starter.adapter.legacy.dto.CaseReqInfoDTO;
import com.yzkj.starter.config.FeignClientConfig;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://127.0.0.1/case_list", name = "caseInfoFeign",
        fallback = CaseInfoFeignFallback.class, configuration = FeignClientConfig.class)
@Component
public interface CaseInfoFeignService {

    @PostMapping(value = "v1/cms/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    JsonNode queryInfo(@RequestParam("userToken") String token, @RequestBody CaseReqInfoDTO caseRequestInfo);
}
