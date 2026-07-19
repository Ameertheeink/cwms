package com.corporatewebsite.websitecontent.entity;

import com.corporatewebsite.common.entity.BaseEntity;
import com.corporatewebsite.websitecontent.enums.WebsiteSection;
import jakarta.persistence.*;

@Entity
@Table(name = "website_contents")
public class WebsiteContent extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(name = "section_key", nullable = false, unique = true)
    private WebsiteSection sectionKey;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 300)
    private String subtitle;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Column(nullable = false)
    private Boolean active = true;

    public WebsiteContent() {
    }



    public WebsiteSection getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(WebsiteSection sectionKey) {
        this.sectionKey = sectionKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}