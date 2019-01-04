package com.alozano.partnerships.interview.model;

import com.alozano.partnerships.interview.converter.LocalDateTimeAttributeConverter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "events")
public class Event implements Serializable {

    private static final long serialVersionUID = -696342004564198490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "venueId", referencedColumnName = "id")
    private Venue venue;
}
