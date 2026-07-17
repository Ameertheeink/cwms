package com.corporatewebsite.event.dto.response;

import com.corporatewebsite.event.enums.EventStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventResponse {

    private Long id;

    private String title;

    private String description;

    private LocalDate eventDate;

    private LocalTime eventTime;

    private String location;

    private EventStatus status;

    private List<EventAssetResponse> assets = new ArrayList<>();

    public EventResponse() {
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public List<EventAssetResponse> getAssets() {
        return assets;
    }

    public void setAssets(List<EventAssetResponse> assets) {
        this.assets = assets;
    }
}