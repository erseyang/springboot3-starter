package com.yzkj.framework.legacy;

import com.yzkj.framework.utils.JsonUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseRespDTO<T> extends LegacyRespDTO {

    @JsonDeserialize(using = LegacyDataDeserializer.class)
    private T data;

    public String toString() {
        return JsonUtils.bean2String(this);
    }
}
