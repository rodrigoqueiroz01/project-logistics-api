package com.dev.logistics.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * @author rodrigoqueiroz
 */

@Getter
@Setter
public class ClientIdRequest {

    @NotNull
    private Long id;

}
