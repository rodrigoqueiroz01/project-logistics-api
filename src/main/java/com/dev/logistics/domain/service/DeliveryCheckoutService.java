package com.dev.logistics.domain.service;

import com.dev.logistics.domain.model.Delivery;
import com.dev.logistics.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rodrigoqueiroz
 */

@Service
@AllArgsConstructor
public class DeliveryCheckoutService {

    private final DeliveryRepository deliveryRepository;
    private final SearchDeliveryService searchDeliveryService;

    @Transactional
    public void finalize(Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);
        delivery.finalize();

        deliveryRepository.save(delivery);
    }

}
