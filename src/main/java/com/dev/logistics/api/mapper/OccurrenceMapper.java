package com.dev.logistics.api.mapper;

import com.dev.logistics.api.dto.response.OccurrenceResponse;
import com.dev.logistics.domain.model.Occurrence;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rodrigoqueiroz
 */

@Component
@AllArgsConstructor
public class OccurrenceMapper {

    private final ModelMapper modelMapper;

    public OccurrenceResponse toModel(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceResponse.class);
    }

    public List<OccurrenceResponse> toCollectionModel(List<Occurrence> occurrences) {
        return occurrences.stream().map(this::toModel).collect(Collectors.toList());
    }

}
