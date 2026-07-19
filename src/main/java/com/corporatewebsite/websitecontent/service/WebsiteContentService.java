package com.corporatewebsite.websitecontent.service;

import com.corporatewebsite.websitecontent.dto.request.WebsiteContentRequest;
import com.corporatewebsite.websitecontent.dto.response.WebsiteContentResponse;
import com.corporatewebsite.websitecontent.enums.WebsiteSection;

import java.util.List;

public interface WebsiteContentService {

    WebsiteContentResponse createWebsiteContent(
            WebsiteContentRequest request);

    WebsiteContentResponse updateWebsiteContent(
            Long id,
            WebsiteContentRequest request);

    WebsiteContentResponse getWebsiteContentById(
            Long id);

    List<WebsiteContentResponse> getAllWebsiteContents();

    WebsiteContentResponse getBySection(
            WebsiteSection section);

    void deleteWebsiteContent(
            Long id);
}