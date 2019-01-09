package com.alozano.partnerships.interview.model.dto;

import com.alozano.partnerships.interview.serializers.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class EventDTO {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    @NotNull
    private VenueDTO venue;
}
