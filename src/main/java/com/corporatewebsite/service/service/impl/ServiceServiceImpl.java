package com.corporatewebsite.service.service.impl;

import com.corporatewebsite.common.exception.ResourceNotFoundException;
import com.corporatewebsite.service.dto.request.ServiceRequest;
import com.corporatewebsite.service.dto.response.ServiceResponse;
import com.corporatewebsite.service.entity.WebsiteService;
import com.corporatewebsite.service.enums.ServiceStatus;
import com.corporatewebsite.service.mapper.ServiceMapper;
import com.corporatewebsite.service.repository.ServiceRepository;
import com.corporatewebsite.service.service.ServiceService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private static final Logger logger =
            LoggerFactory.getLogger(ServiceServiceImpl.class);

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository,
                              ServiceMapper serviceMapper) {

        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public ServiceResponse createService(ServiceRequest request) {

        logger.info("Creating new service: {}", request.getTitle());

        WebsiteService service = serviceMapper.toEntity(request);

        WebsiteService savedService = serviceRepository.save(service);

        logger.info("Service created successfully with id: {}",
                savedService.getId());

        return serviceMapper.toResponse(savedService);
    }

    @Override
    public ServiceResponse updateService(Long serviceId,
                                         ServiceRequest request) {

        logger.info("Updating service id: {}", serviceId);

        WebsiteService service = serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Service not found with id: " + serviceId));

        serviceMapper.updateEntity(service, request);

        WebsiteService updatedService = serviceRepository.save(service);

        logger.info("Service updated successfully: {}", serviceId);

        return serviceMapper.toResponse(updatedService);
    }

    @Override
    public ServiceResponse getServiceById(Long serviceId) {

        logger.info("Fetching service id: {}", serviceId);

        WebsiteService service = serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Service not found with id: " + serviceId));

        return serviceMapper.toResponse(service);
    }

    @Override
    public List<ServiceResponse> getAllServices() {

        logger.info("Fetching all services");

        return serviceRepository
                .findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(serviceMapper::toResponse)
                .toList();
    }

    @Override
    public List<ServiceResponse> getActiveServices() {

        logger.info("Fetching active services");

        return serviceRepository
                .findByStatusOrderByDisplayOrderAsc(
                        ServiceStatus.ACTIVE)
                .stream()
                .map(serviceMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteService(Long serviceId) {

        logger.info("Deleting service id: {}", serviceId);

        WebsiteService service = serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Service not found with id: " + serviceId));

        serviceRepository.delete(service);

        logger.info("Service deleted successfully: {}", serviceId);
    }
}