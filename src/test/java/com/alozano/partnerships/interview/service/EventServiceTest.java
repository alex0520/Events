package com.alozano.partnerships.interview.service;

import com.alozano.partnerships.interview.model.Event;
import com.alozano.partnerships.interview.model.Venue;
import com.alozano.partnerships.interview.model.dto.EventDTO;
import com.alozano.partnerships.interview.model.dto.VenueDTO;
import com.alozano.partnerships.interview.repository.EventRepository;
import com.alozano.partnerships.interview.repository.VenueRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.alozano.partnerships.interview.mapper.IEventMapperImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventService.class, IEventMapperImpl.class})
public class EventServiceTest {

    @MockBean
    EventRepository eventRepository;

    @MockBean
    VenueRepository venueRepository;

    @Autowired
    EventService eventService;

    @Before
    public void setUp() throws Exception {
        Venue venue = new Venue();
        venue.setId(2);
        venue.setCity("Test Venue City");
        venue.setState("FL");
        venue.setName("Test Venue Name");

        Event event = new Event();
        event.setId(1);
        event.setName("New Test Event");

        event.setVenue(venue);

        List<Event> events = new ArrayList<>();
        events.add(event);

        Mockito.when(eventRepository.findAll()).thenReturn(events);
        Mockito.when(eventRepository.save(any(Event.class))).thenReturn(event);
        Mockito.when(venueRepository.save(any(Venue.class))).thenReturn(venue);
    }

    @Test
    public void Should_Return_Events_Correctly() {

        List<EventDTO> serviceEvents = eventService.getEvents();
        assertNotNull(serviceEvents);
        assertEquals(serviceEvents.size(),1);
        EventDTO eventDTO = serviceEvents.get(0);
        assertNotNull(eventDTO);
        assertEquals(1, (int) eventDTO.getId());
        VenueDTO venueDTO = eventDTO.getVenue();
        assertEquals(2, (int) venueDTO.getId());
    }

    @Test
    public void Should_Create_Event_Correctly() {
        EventDTO eventDTO = new EventDTO();

        EventDTO event = eventService.createEvent(eventDTO);

        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void Should_Create_Event_With_Venue_Correctly() {
        VenueDTO venueDTO = new VenueDTO();
        EventDTO eventDTO = new EventDTO();
        eventDTO.setVenue(venueDTO);

        EventDTO event = eventService.createEventWithVenue(eventDTO);

        verify(eventRepository, times(1)).save(any(Event.class));
        verify(venueRepository, times(1)).save(any(Venue.class));
    }
}