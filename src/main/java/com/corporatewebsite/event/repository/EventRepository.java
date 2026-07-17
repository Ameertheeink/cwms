package com.corporatewebsite.event.repository;

import com.corporatewebsite.event.entity.Event;
import com.corporatewebsite.event.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findByStatus(EventStatus status);

    List<Event> findByTitleContainingIgnoreCase(String title);

    List<Event> findByEventDateGreaterThanEqual(LocalDate date);

    List<Event> findAllByOrderByEventDateDesc();

}
