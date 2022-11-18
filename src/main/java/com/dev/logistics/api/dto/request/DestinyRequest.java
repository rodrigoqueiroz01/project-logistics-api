package com.dev.logistics.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author rodrigoqueiroz
 */

@Getter
@Setter
public class DestinyRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private Integer number;

    private String complement;

    @NotBlank
    private String district;

}
