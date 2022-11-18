package com.dev.logistics.api.mapper;

import com.dev.logistics.api.dto.response.DeliveryResponse;
import com.dev.logistics.domain.model.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rodrigoqueiroz
 */

@Component
@AllArgsConstructor
public class DeliveryMapper {

    private final ModelMapper modelMapper;

    public DeliveryResponse toModel(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryResponse.class);
    }

    public List<DeliveryResponse> toCollectionModel(List<Delivery> deliverys) {
        return deliverys.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Delivery toEntity(DeliveryMapper deliveryMapper) {
        return modelMapper.map(deliveryMapper, Delivery.class);
    }

}
