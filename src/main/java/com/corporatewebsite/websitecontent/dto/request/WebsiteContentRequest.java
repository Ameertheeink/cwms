package com.corporatewebsite.websitecontent.dto.request;

import com.corporatewebsite.common.enums.Status;
import com.corporatewebsite.websitecontent.enums.WebsiteSection;
import jakarta.validation.constraints.*;

public class WebsiteContentRequest {

    @NotNull(message = "Section is required.")
    private WebsiteSection sectionKey;

    @NotBlank(message = "Title is required.")
    @Size(max = 200)
    private String title;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Size(max = 300)
    private String subtitle;

    @NotBlank(message = "Content is required.")
    private String content;

    @NotNull(message = "Display order is required.")
    @Min(1)
    @Max(100)
    private Integer displayOrder;
    @NotNull(message = "Active status is required.")
    private Boolean active;

    public WebsiteContentRequest() {
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