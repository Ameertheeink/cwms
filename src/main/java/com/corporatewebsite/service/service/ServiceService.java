package com.corporatewebsite.service.service;

import com.corporatewebsite.service.dto.request.ServiceRequest;
import com.corporatewebsite.service.dto.response.ServiceResponse;

import java.util.List;

public interface ServiceService {

    /**
     * Create a new service.
     */
    ServiceResponse createService(ServiceRequest request);

    /**
     * Update an existing service.
     */
    ServiceResponse updateService(Long serviceId,
                                  ServiceRequest request);

    /**
     * Get service by id.
     */
    ServiceResponse getServiceById(Long serviceId);

    /**
     * Get all services.
     */
    List<ServiceResponse> getAllServices();

    /**
     * Get only active services ordered by display order.
     */
    List<ServiceResponse> getActiveServices();

    /**
     * Delete service.
     */
    void deleteService(Long serviceId);
}