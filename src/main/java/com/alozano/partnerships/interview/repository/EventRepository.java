package com.alozano.partnerships.interview.repository;

import com.alozano.partnerships.interview.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {
    List<Event> findAll();
}
