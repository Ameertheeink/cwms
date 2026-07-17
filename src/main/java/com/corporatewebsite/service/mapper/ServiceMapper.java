package com.corporatewebsite.service.mapper;

import com.corporatewebsite.service.dto.request.ServiceRequest;
import com.corporatewebsite.service.dto.response.ServiceResponse;
import com.corporatewebsite.service.entity.WebsiteService;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    /**
     * Convert Request DTO -> Entity
     */
    public WebsiteService toEntity(ServiceRequest request) {

        if (request == null) {
            return null;
        }

        WebsiteService service = new WebsiteService();

        service.setTitle(request.getTitle());
        service.setShortDescription(request.getShortDescription());
        service.setDescription(request.getDescription());
        service.setDisplayOrder(request.getDisplayOrder());
        service.setStatus(request.getStatus());

        return service;
    }

    /**
     * Convert Entity -> Response DTO
     */
    public ServiceResponse toResponse(WebsiteService service) {

        if (service == null) {
            return null;
        }

        ServiceResponse response = new ServiceResponse();

        response.setId(service.getId());
        response.setTitle(service.getTitle());
        response.setShortDescription(service.getShortDescription());
        response.setDescription(service.getDescription());
        response.setDisplayOrder(service.getDisplayOrder());
        response.setStatus(service.getStatus());

        return response;
    }

    /**
     * Update existing entity from request
     */
    public void updateEntity(WebsiteService service,
                             ServiceRequest request) {

        service.setTitle(request.getTitle());
        service.setShortDescription(request.getShortDescription());
        service.setDescription(request.getDescription());
        service.setDisplayOrder(request.getDisplayOrder());
        service.setStatus(request.getStatus());
    }
}