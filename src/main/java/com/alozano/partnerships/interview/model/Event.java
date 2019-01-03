package com.alozano.partnerships.interview.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "events")
public class Event implements Serializable {

    private static final long serialVersionUID = -696342004564198490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "venueId", referencedColumnName = "id")
    private Venue venue;
}
