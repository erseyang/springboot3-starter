package com.yzkj.framework.legacy;

import com.yzkj.framework.utils.JsonUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseListRespDTO<T extends BaseLegacyDTO> extends LegacyRespDTO {

    private List<T> data;

    public String toString() {
        return JsonUtils.bean2String(this);
    }
}
