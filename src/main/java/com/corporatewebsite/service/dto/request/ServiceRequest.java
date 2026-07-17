package com.corporatewebsite.service.dto.request;

import com.corporatewebsite.service.enums.ServiceStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ServiceRequest {

    @NotBlank(message = "Title is required.")
    @Size(max = 150, message = "Title cannot exceed 150 characters.")
    private String title;

    @NotBlank(message = "Short description is required.")
    @Size(max = 300, message = "Short description cannot exceed 300 characters.")
    private String shortDescription;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotNull(message = "Display order is required.")
    @Min(value = 1, message = "Display order must be at least 1.")
    @Max(value = 999, message = "Display order cannot exceed 999.")
    private Integer displayOrder;

    @NotNull(message = "Status is required.")
    private ServiceStatus status;

    public ServiceRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }
}