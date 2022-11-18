package com.dev.logistics.domain.service;

import com.dev.logistics.domain.model.Client;
import com.dev.logistics.domain.model.Delivery;
import com.dev.logistics.domain.model.enums.DeliveryStatus;
import com.dev.logistics.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;

/**
 * @author rodrigoqueiroz
 */

@Service
@AllArgsConstructor
public class DeliveryRequestService {

    private final DeliveryRepository deliveryRepository;
    private final CustomerCatalogService customerCatalogService;

    @Transactional
    public Delivery request(Delivery delivery) {
        Client client = customerCatalogService.search(delivery.getClient().getId());

        delivery.setStatus(DeliveryStatus.PENDENTE);
        delivery.setOrderDate(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }

}
