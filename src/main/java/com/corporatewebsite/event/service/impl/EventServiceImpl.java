package com.corporatewebsite.event.service.impl;

import com.corporatewebsite.common.exception.ResourceNotFoundException;
import com.corporatewebsite.event.dto.request.EventRequest;
import com.corporatewebsite.event.dto.response.EventResponse;
import com.corporatewebsite.event.entity.Event;
import com.corporatewebsite.event.enums.EventStatus;
import com.corporatewebsite.event.mapper.EventMapper;
import com.corporatewebsite.event.repository.EventRepository;
import com.corporatewebsite.event.service.EventService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository,
                            EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventResponse createEvent(EventRequest request) {

        log.info("Creating event with title: {}", request.getTitle());

        Event event = eventMapper.toEntity(request);

        Event savedEvent = eventRepository.save(event);

        log.info("Event created successfully with ID: {}", savedEvent.getId());

        return eventMapper.toResponse(savedEvent);
    }

    @Override
    public EventResponse updateEvent(Long eventId, EventRequest request) {

        log.info("Updating event with ID: {}", eventId);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    log.warn("Event not found with ID: {}", eventId);
                    return new ResourceNotFoundException(
                            "Event not found with id : " + eventId);
                });

        eventMapper.updateEntity(event, request);

        Event updatedEvent = eventRepository.save(event);

        log.info("Event updated successfully with ID: {}", updatedEvent.getId());

        return eventMapper.toResponse(updatedEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse getEventById(Long eventId) {

        log.info("Fetching event with ID: {}", eventId);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    log.warn("Event not found with ID: {}", eventId);
                    return new ResourceNotFoundException(
                            "Event not found with id : " + eventId);
                });

        log.info("Event fetched successfully with ID: {}", eventId);

        return eventMapper.toResponse(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvents() {

        log.info("Fetching all events");

        List<EventResponse> events = eventRepository.findAll()
                .stream()
                .map(eventMapper::toResponse)
                .toList();

        log.info("Fetched {} event(s)", events.size());

        return events;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getActiveEvents() {

        log.info("Fetching all active events");

        List<EventResponse> activeEvents = eventRepository.findByStatus(EventStatus.ACTIVE)
                .stream()
                .map(eventMapper::toResponse)
                .toList();

        log.info("Fetched {} active event(s)", activeEvents.size());

        return activeEvents;
    }

    @Override
    public void deleteEvent(Long eventId) {

        log.info("Deleting event with ID: {}", eventId);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    log.warn("Event not found with ID: {}", eventId);
                    return new ResourceNotFoundException(
                            "Event not found with id : " + eventId);
                });

        eventRepository.delete(event);

        log.info("Event deleted successfully with ID: {}", eventId);
    }
}