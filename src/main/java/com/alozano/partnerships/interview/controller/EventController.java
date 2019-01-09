package com.alozano.partnerships.interview.controller;

import com.alozano.partnerships.interview.model.dto.EventDTO;
import com.alozano.partnerships.interview.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    @Autowired
    public EventController(final EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> getEvents() {
        List<EventDTO> events = eventService.getEvents();
        LOGGER.info("Returning {} events", events.size());
        return events;
    }

    @PostMapping
    public EventDTO createEvents(@RequestBody EventDTO eventDTO) {
        EventDTO event = eventService.createEvent(eventDTO);
        LOGGER.info("Creating {} event", event.getId());
        return event;
    }

    @PostMapping("/venue")
    public EventDTO createEventsWithVenue(@RequestBody EventDTO eventDTO) {
        EventDTO event = eventService.createEventWithVenue(eventDTO);
        LOGGER.info("Creating {} venue", event.getVenue().getId());
        LOGGER.info("Creating {} event", event.getId());
        return event;
    }
}
