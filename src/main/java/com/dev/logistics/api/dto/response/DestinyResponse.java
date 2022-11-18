package com.dev.logistics.api.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rodrigoqueiroz
 */

@Getter
@Setter
public class DestinyResponse {

    private String name;
    private String address;
    private String number;
    private String complement;
    private String district;

}
