package com.dev.logistics.api.controller;

import com.dev.logistics.api.dto.request.OccurrenceRequest;
import com.dev.logistics.api.dto.response.OccurrenceResponse;
import com.dev.logistics.api.mapper.OccurrenceMapper;
import com.dev.logistics.domain.model.Delivery;
import com.dev.logistics.domain.model.Occurrence;
import com.dev.logistics.domain.service.OccurrenceRecordService;
import com.dev.logistics.domain.service.SearchDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @author rodrigoqueiroz
 */

@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
@AllArgsConstructor
@Validated
public class OccurrencesController {

    private final SearchDeliveryService searchDeliveryService;
    private final OccurrenceRecordService occurrenceRecordService;
    private final OccurrenceMapper occurrenceMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceResponse register(@PathVariable Long deliveryId, @Valid @RequestBody OccurrenceRequest occurrenceRequest) {
        Occurrence registeredOccurrence = occurrenceRecordService.register(deliveryId, occurrenceRequest.getDescription());

        return occurrenceMapper.toModel(registeredOccurrence);
    }

    @GetMapping
    public List<OccurrenceResponse> list(@PathVariable Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);

        return occurrenceMapper.toCollectionModel(delivery.getOccurrences());
    }

}

