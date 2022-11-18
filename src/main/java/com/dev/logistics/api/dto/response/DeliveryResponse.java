package com.dev.logistics.api.dto.response;

import com.dev.logistics.domain.model.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @author rodrigoqueiroz
 */

@Getter
@Setter
public class DeliveryResponse {

    private Long id;
    private ClientSummaryResponse client;
    private DeliveryResponse delivery;
    private BigDecimal tax;
    private DeliveryStatus status;
    private OffsetDateTime orderDate;
    private OffsetDateTime completionDate;

}
