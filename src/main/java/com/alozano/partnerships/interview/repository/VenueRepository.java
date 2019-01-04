package com.alozano.partnerships.interview.repository;

import com.alozano.partnerships.interview.model.Venue;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends PagingAndSortingRepository<Venue, Integer> {
}
