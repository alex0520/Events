package com.alozano.partnerships.interview.mapper;

import com.alozano.partnerships.interview.model.Venue;
import com.alozano.partnerships.interview.model.dto.VenueDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IVenueMapper {
    VenueDTO venueToVenueDTO(Venue venue);

    Venue venueDTOToVenue(VenueDTO venueDTO);
}
