package com.alozano.partnerships.interview.service;

import com.alozano.partnerships.interview.mapper.IEventMapper;
import com.alozano.partnerships.interview.model.Event;
import com.alozano.partnerships.interview.model.dto.EventDTO;
import com.alozano.partnerships.interview.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final IEventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, IEventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getEvents() {
         return this.eventMapper.eventsToEventDTOs(eventRepository.findAll());
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = eventRepository.save(this.eventMapper.eventDTOToEvent(eventDTO));
        return this.eventMapper.eventToEventDTO(event);
    }
}
