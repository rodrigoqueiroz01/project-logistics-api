package com.dev.logistics.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author rodrigoqueiroz
 */

@Getter
@Setter
public class DeliveryRequest {

    @Valid
    @NotNull
    private ClientIdRequest cliente;

    @Valid
    @NotNull
    private DestinyRequest destinatario;

    private BigDecimal taxa;

}
