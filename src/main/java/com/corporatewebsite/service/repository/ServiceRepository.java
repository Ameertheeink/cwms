package com.corporatewebsite.service.repository;

import com.corporatewebsite.service.entity.WebsiteService;
import com.corporatewebsite.service.enums.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<WebsiteService, Long> {

    /**
     * Returns all active services.
     */
    List<WebsiteService> findByStatus(ServiceStatus status);

    /**
     * Returns services ordered by display order.
     */
    List<WebsiteService> findAllByOrderByDisplayOrderAsc();

    /**
     * Returns active services ordered by display order.
     */
    List<WebsiteService> findByStatusOrderByDisplayOrderAsc(
            ServiceStatus status);
}