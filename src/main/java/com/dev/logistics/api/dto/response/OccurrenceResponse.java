package com.dev.logistics.api.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;

/**
 * @author rodrigoqueiroz
 */

@Getter
@Setter
public class OccurrenceResponse {

    private Long id;
    private String description;
    private OffsetDateTime registrationDate;

}
