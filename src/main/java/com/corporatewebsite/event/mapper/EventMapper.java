package com.corporatewebsite.event.mapper;

import com.corporatewebsite.event.dto.request.EventRequest;
import com.corporatewebsite.event.dto.response.EventResponse;
import com.corporatewebsite.event.entity.Event;
import com.corporatewebsite.event.entity.EventAsset;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    private final EventAssetMapper eventAssetMapper;

    public EventMapper(EventAssetMapper eventAssetMapper) {
        this.eventAssetMapper = eventAssetMapper;
    }

    /**
     * Convert Request DTO to Entity
     */
    public Event toEntity(EventRequest request) {

        if (request == null) {
            return null;
        }

        Event event = new Event();

        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setEventTime(request.getEventTime());
        event.setLocation(request.getLocation());
        event.setStatus(request.getStatus());

        return event;
    }

    /**
     * Convert Entity to Response DTO
     */
    public EventResponse toResponse(Event event) {

        if (event == null) {
            return null;
        }

        EventResponse response = new EventResponse();

        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setDescription(event.getDescription());
        response.setEventDate(event.getEventDate());
        response.setEventTime(event.getEventTime());
        response.setLocation(event.getLocation());
        response.setStatus(event.getStatus());

        List<EventAsset> assets = event.getAssets();

        if (assets != null) {
            response.setAssets(
                    assets.stream()
                            .map(eventAssetMapper::toResponse)
                            .collect(Collectors.toList())
            );
        }

        return response;
    }
    public void updateEntity(Event event, EventRequest request) {

        if (event == null || request == null) {
            return;
        }

        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setEventTime(request.getEventTime());
        event.setLocation(request.getLocation());
        event.setStatus(request.getStatus());
    }

}