package com.dev.logistics.api.controller;

import com.dev.logistics.api.dto.request.DeliveryRequest;
import com.dev.logistics.api.dto.response.DeliveryResponse;
import com.dev.logistics.api.mapper.DeliveryMapper;
import com.dev.logistics.domain.model.Delivery;
import com.dev.logistics.domain.repository.DeliveryRepository;
import com.dev.logistics.domain.service.DeliveryCheckoutService;
import com.dev.logistics.domain.service.DeliveryRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @author rodrigoqueiroz
 */

@RestController
@RequestMapping("/deliveries")
@AllArgsConstructor
@Validated
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final DeliveryRequestService deliveryRequestService;
    private final DeliveryCheckoutService deliveryCheckoutService;

    @PostMapping("/request")
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryResponse request(@Valid @RequestBody DeliveryRequest deliveryRequest) {
        Delivery newDelivery = deliveryMapper.toEntity(deliveryMapper);
        Delivery requestedDelivery = deliveryRequestService.request(newDelivery);

        return deliveryMapper.toModel(requestedDelivery);
    }

    @GetMapping("/all")
    public List<DeliveryResponse> list() {
        return deliveryMapper.toCollectionModel(deliveryRepository.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryResponse> search(@PathVariable Long deliveryId) {
        return deliveryRepository.findById(deliveryId).map(delivery -> ResponseEntity.ok(deliveryMapper.toModel(delivery)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{deliveryId}/finalization")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalize(@PathVariable Long deliveryId) {
        deliveryCheckoutService.finalize(deliveryId);
    }

}
