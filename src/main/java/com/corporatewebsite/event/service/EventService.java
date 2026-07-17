package com.corporatewebsite.event.service;

import com.corporatewebsite.event.dto.request.EventRequest;
import com.corporatewebsite.event.dto.response.EventResponse;

import java.util.List;

public interface EventService {

    EventResponse createEvent(EventRequest request);

    EventResponse updateEvent(Long eventId, EventRequest request);

    EventResponse getEventById(Long eventId);

    List<EventResponse> getAllEvents();

    List<EventResponse> getActiveEvents();

    void deleteEvent(Long eventId);
}