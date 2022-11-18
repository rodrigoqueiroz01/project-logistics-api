package com.dev.logistics.domain.service;

import com.dev.logistics.domain.model.Delivery;
import com.dev.logistics.domain.model.Occurrence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rodrigoqueiroz
 */

@Service
@AllArgsConstructor
public class OccurrenceRecordService {

    private final SearchDeliveryService searchDeliveryService;

    @Transactional
    public Occurrence register(Long deliveryId, String description) {
        Delivery delivery = searchDeliveryService.search(deliveryId);

        return delivery.addOccurrence(description);
    }

}
