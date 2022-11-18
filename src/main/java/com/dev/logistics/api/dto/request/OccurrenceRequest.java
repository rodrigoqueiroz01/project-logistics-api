package com.dev.logistics.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

/**
 * @author rodrigoqueiroz
 */

@Getter
@Setter
public class OccurrenceRequest {

    @NotBlank
    private String description;

}
