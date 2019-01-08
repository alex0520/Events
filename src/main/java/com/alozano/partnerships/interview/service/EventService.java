package com.alozano.partnerships.interview.service;

import com.alozano.partnerships.interview.mapper.IEventMapper;
import com.alozano.partnerships.interview.model.Event;
import com.alozano.partnerships.interview.model.Venue;
import com.alozano.partnerships.interview.model.dto.EventDTO;
import com.alozano.partnerships.interview.repository.EventRepository;
import com.alozano.partnerships.interview.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final IEventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, VenueRepository venueRepository, IEventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getEvents() {
        return this.eventMapper.eventsToEventDTOs(eventRepository.findAll());
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = eventRepository.save(this.eventMapper.eventDTOToEvent(eventDTO));
        return this.eventMapper.eventToEventDTO(event);
    }

    @Transactional
    public EventDTO createEventWithVenue(EventDTO eventDTO) {
        Event event = this.eventMapper.eventDTOToEvent(eventDTO);
        Venue venue = event.getVenue();
        venue = this.venueRepository.save(venue);
        event.setVenue(venue);
        event = this.eventRepository.save(event);
        return this.eventMapper.eventToEventDTO(event);
    }
}
