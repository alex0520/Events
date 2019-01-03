package com.alozano.partnerships.interview.repository;

import com.alozano.partnerships.interview.model.Event;
import com.alozano.partnerships.interview.model.Venue;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {
    List<Event> findAll();
}
