package com.yzkj.starter.adapter.legacy.dto;

import com.yzkj.framework.legacy.BaseLegacyDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 防腐屋的数据交互对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseInfoDTO extends BaseLegacyDTO {

    /**
     * 案件ID
     */
    private String id;

    /**
     * 批次号
     */
    private String batchCode;

    /**
     * 帐号
     */
    private String accountNo;

    /**
     * 案件编号
     */
    private String caseNo;
}
