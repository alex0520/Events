package com.alozano.partnerships.interview.model.dto;

import com.alozano.partnerships.interview.serializers.LocalDateTimeDeserializer;
import com.alozano.partnerships.interview.serializers.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;

    @NotNull
    private VenueDTO venue;
}
