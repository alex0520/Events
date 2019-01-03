package com.alozano.partnerships.interview.mapper;

import com.alozano.partnerships.interview.model.Event;
import com.alozano.partnerships.interview.model.dto.EventDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEventMapper {
    EventDTO eventToEventDTO(Event event);

    Event eventDTOToEvent(EventDTO eventDTO);

    List<EventDTO> eventsToEventDTOs(List<Event> events);

    List<Event> eventDTOsToEvents(List<EventDTO> eventDTOs);
}
