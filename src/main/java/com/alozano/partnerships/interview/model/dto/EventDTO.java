package com.alozano.partnerships.interview.model.dto;

import com.alozano.partnerships.interview.model.Venue;
import com.alozano.partnerships.interview.serializers.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
