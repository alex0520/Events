package com.alozano.partnerships.interview.repository;

import com.alozano.partnerships.interview.model.Venue;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VenueRepository extends PagingAndSortingRepository<Venue, Integer> {
}
