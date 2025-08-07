package com.yzkj.framework.legacy;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LegacyRespDTO implements LegacyInterface {

    private Integer code;

    private String message;


}
