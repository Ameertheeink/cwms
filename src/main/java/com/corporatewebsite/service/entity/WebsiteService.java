package com.corporatewebsite.service.entity;

import com.corporatewebsite.common.entity.BaseEntity;
import com.corporatewebsite.service.enums.ServiceStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class WebsiteService extends BaseEntity {



    @Column(nullable = false, length = 150)
    private String title;

    @Column(name = "short_description",
            nullable = false,
            length = 300)
    private String shortDescription;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceStatus status = ServiceStatus.ACTIVE;

    public WebsiteService() {
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