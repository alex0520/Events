package com.alozano.partnerships.interview.controller;

import com.alozano.partnerships.interview.model.dto.EventDTO;
import com.alozano.partnerships.interview.model.dto.VenueDTO;
import com.alozano.partnerships.interview.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService eventService;

    private EventDTO prepareEventDTO(){
        VenueDTO venueDTO = new VenueDTO();
        venueDTO.setId(2);
        venueDTO.setName("Venue Test Name");
        venueDTO.setCity("Venue Test City");
        venueDTO.setState("FL");

        EventDTO event = new EventDTO();
        event.setId(1);
        event.setName("Event Test Name");
        event.setDate(LocalDateTime.now());
        event.setVenue(venueDTO);

        return event;
    }

    private EventDTO prepareCreateEventDTO(){
        EventDTO eventDTO = prepareEventDTO();
        eventDTO.setId(null);
        return eventDTO;
    }

    private EventDTO prepareCreateEventWithVenueDTO(){
        EventDTO eventDTO = prepareEventDTO();
        eventDTO.setId(null);
        eventDTO.getVenue().setId(null);
        return eventDTO;
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();

            JavaTimeModule module=new JavaTimeModule();
            mapper.registerModule(module);
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void Should_Return_Events_Correctly() throws Exception {

        EventDTO event = prepareEventDTO();

        List<EventDTO> allEvents = Arrays.asList(event);

        given(eventService.getEvents()).willReturn(allEvents);

        mvc.perform(get("/api/events")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(event.getName())));
    }

    @Test
    public void Should_Create_Events_Correctly() throws Exception {

        EventDTO createEventDTO = prepareCreateEventDTO();
        EventDTO eventDTO = prepareEventDTO();

        Mockito.when(eventService.createEvent(any(EventDTO.class))).thenReturn(eventDTO);
        mvc.perform(post("/api/events")
                .content(asJsonString(createEventDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(eventDTO.getId())));

        verify(eventService, times(1)).createEvent(createEventDTO);
    }

    @Test
    public void Should_Create_Events_With_Venue_Correctly() throws Exception {
        EventDTO createEventDTO = prepareCreateEventWithVenueDTO();
        EventDTO eventDTO = prepareEventDTO();

        Mockito.when(eventService.createEventWithVenue(any(EventDTO.class))).thenReturn(eventDTO);
        mvc.perform(post("/api/events/venue")
                .content(asJsonString(createEventDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(eventDTO.getId())))
                .andExpect(jsonPath("$.venue.id", is(eventDTO.getVenue().getId())));

        verify(eventService, times(1)).createEventWithVenue(createEventDTO);
    }
}