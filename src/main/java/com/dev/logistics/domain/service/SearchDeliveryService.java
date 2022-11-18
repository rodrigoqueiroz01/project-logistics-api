package com.dev.logistics.domain.service;

import com.dev.logistics.domain.exception.EntityNotFoundException;
import com.dev.logistics.domain.model.Delivery;
import com.dev.logistics.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author rodrigoqueiroz
 */

@Service
@AllArgsConstructor
public class SearchDeliveryService {

    private final DeliveryRepository deliveryRepository;

    public Delivery search(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada."));
    }

}
