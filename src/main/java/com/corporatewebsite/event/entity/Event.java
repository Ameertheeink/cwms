package com.corporatewebsite.event.entity;

import com.corporatewebsite.common.entity.BaseEntity;
import com.corporatewebsite.event.enums.EventStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    @Column(name="title",nullable = false,length = 200)
    private String title;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;
    @Column(name = "event_time")
    private LocalTime eventTime;
    @Column(name = "location", length = 255)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private EventStatus status;

    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @OrderBy("displayOrder ASC")
    private List<EventAsset> assets = new ArrayList<>();
    public Event() {
    }
    public void addAsset(EventAsset asset) {
        assets.add(asset);
        asset.setEvent(this);
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

    public List<EventAsset> getAssets() {
        return assets;
    }

    public void setAssets(List<EventAsset> assets) {
        this.assets = assets;
    }

    /**
     * Helper method to maintain both sides of the relationship.
     */
    public void removeAsset(EventAsset asset) {
        assets.remove(asset);
        asset.setEvent(null);
    }

}