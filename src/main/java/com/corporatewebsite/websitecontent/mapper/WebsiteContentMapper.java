package com.corporatewebsite.websitecontent.mapper;

import com.corporatewebsite.websitecontent.dto.response.WebsiteContentResponse;
import com.corporatewebsite.websitecontent.entity.WebsiteContent;
import org.springframework.stereotype.Component;

@Component
public class WebsiteContentMapper {

    public WebsiteContentResponse toResponse(WebsiteContent content) {

        if (content == null) {
            return null;
        }

        WebsiteContentResponse response =
                new WebsiteContentResponse();

        response.setId(content.getId());
        response.setSectionKey(content.getSectionKey());
        response.setTitle(content.getTitle());
        response.setSubtitle(content.getSubtitle());
        response.setContent(content.getContent());
        response.setDisplayOrder(content.getDisplayOrder());
        response.setActive(content.getActive());

        return response;
    }
}