package com.corporatewebsite.websitecontent.dto.response;

import com.corporatewebsite.common.enums.Status;
import com.corporatewebsite.websitecontent.enums.WebsiteSection;

public class WebsiteContentResponse {

    private Long id;

    private WebsiteSection sectionKey;

    private String title;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private String subtitle;

    private String content;

    private Integer displayOrder;

    private Boolean active;

    public WebsiteContentResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}