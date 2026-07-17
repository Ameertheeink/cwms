package com.corporatewebsite.service.controller;

import com.corporatewebsite.common.response.ApiResponse;
import com.corporatewebsite.service.dto.request.ServiceRequest;
import com.corporatewebsite.service.dto.response.ServiceResponse;
import com.corporatewebsite.service.service.ServiceService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private static final Logger logger =
            LoggerFactory.getLogger(ServiceController.class);

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    /**
     * Create Service
     */
    @PostMapping
    public ResponseEntity<ApiResponse<ServiceResponse>> createService(
            @Valid @RequestBody ServiceRequest request) {

        logger.info("POST /api/services");

        ServiceResponse response =
                serviceService.createService(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Service created successfully",
                        response));
    }

    /**
     * Update Service
     */
    @PutMapping("/{serviceId}")
    public ResponseEntity<ApiResponse<ServiceResponse>> updateService(
            @PathVariable Long serviceId,
            @Valid @RequestBody ServiceRequest request) {

        logger.info("PUT /api/services/{}", serviceId);

        ServiceResponse response =
                serviceService.updateService(serviceId, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Service updated successfully",
                        response));
    }

    /**
     * Get Service By Id
     */
    @GetMapping("/{serviceId}")
    public ResponseEntity<ApiResponse<ServiceResponse>> getServiceById(
            @PathVariable Long serviceId) {

        logger.info("GET /api/services/{}", serviceId);

        ServiceResponse response =
                serviceService.getServiceById(serviceId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Service fetched successfully",
                        response));
    }

    /**
     * Get All Services
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceResponse>>> getAllServices() {

        logger.info("GET /api/services");

        List<ServiceResponse> response =
                serviceService.getAllServices();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Services fetched successfully",
                        response));
    }

    /**
     * Get Active Services
     */
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<ServiceResponse>>> getActiveServices() {

        logger.info("GET /api/services/active");

        List<ServiceResponse> response =
                serviceService.getActiveServices();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Active services fetched successfully",
                        response));
    }

    /**
     * Delete Service
     */
    @DeleteMapping("/{serviceId}")
    public ResponseEntity<ApiResponse<Void>> deleteService(
            @PathVariable Long serviceId) {

        logger.info("DELETE /api/services/{}", serviceId);

        serviceService.deleteService(serviceId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Service deleted successfully",
                        null));
    }
}