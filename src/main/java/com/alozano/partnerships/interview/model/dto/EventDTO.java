package com.alozano.partnerships.interview.model.dto;

import com.alozano.partnerships.interview.model.Venue;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class EventDTO {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Date date;

    @NotNull
    private Venue venue;
}
