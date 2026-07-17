package com.corporatewebsite.event.controller;

import com.corporatewebsite.common.response.ApiResponse;
import com.corporatewebsite.event.dto.request.EventRequest;
import com.corporatewebsite.event.dto.response.EventResponse;
import com.corporatewebsite.event.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EventResponse>> createEvent(
            @Valid @RequestBody EventRequest request) {

        EventResponse response = eventService.createEvent(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Event created successfully",
                        response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EventResponse>>> getAllEvents() {

        List<EventResponse> response = eventService.getAllEvents();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Events fetched successfully",
                        response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponse>> getEventById(
            @PathVariable Long id) {

        EventResponse response = eventService.getEventById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Event fetched successfully",
                        response));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<EventResponse>>> getActiveEvents() {

        List<EventResponse> response = eventService.getActiveEvents();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Active events fetched successfully",
                        response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponse>> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest request) {

        EventResponse response = eventService.updateEvent(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Event updated successfully",
                        response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(
            @PathVariable Long id) {

        eventService.deleteEvent(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Event deleted successfully",
                        null));
    }
}