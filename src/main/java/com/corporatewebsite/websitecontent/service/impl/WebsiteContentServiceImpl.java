package com.corporatewebsite.websitecontent.service.impl;

import com.corporatewebsite.common.exception.DuplicateResourceException;
import com.corporatewebsite.common.exception.ResourceNotFoundException;
import com.corporatewebsite.websitecontent.dto.request.WebsiteContentRequest;
import com.corporatewebsite.websitecontent.dto.response.WebsiteContentResponse;
import com.corporatewebsite.websitecontent.entity.WebsiteContent;
import com.corporatewebsite.websitecontent.enums.WebsiteSection;
import com.corporatewebsite.websitecontent.mapper.WebsiteContentMapper;
import com.corporatewebsite.websitecontent.repository.WebsiteContentRepository;
import com.corporatewebsite.websitecontent.service.WebsiteContentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WebsiteContentServiceImpl implements WebsiteContentService {

    private static final Logger logger =
            LoggerFactory.getLogger(WebsiteContentServiceImpl.class);

    private final WebsiteContentRepository websiteContentRepository;
    private final WebsiteContentMapper websiteContentMapper;

    public WebsiteContentServiceImpl(
            WebsiteContentRepository websiteContentRepository,
            WebsiteContentMapper websiteContentMapper) {

        this.websiteContentRepository = websiteContentRepository;
        this.websiteContentMapper = websiteContentMapper;
    }

    @Override
    public WebsiteContentResponse createWebsiteContent(
            WebsiteContentRequest request) {

        logger.info("Creating website content for section: {}",
                request.getSectionKey());

        if (websiteContentRepository.existsBySectionKey(
                request.getSectionKey())) {

            throw new DuplicateResourceException(
                    "Website content already exists for section: "
                            + request.getSectionKey());
        }

        WebsiteContent content = new WebsiteContent();

        content.setSectionKey(request.getSectionKey());
        content.setTitle(request.getTitle());
        content.setSubtitle(request.getSubtitle());
        content.setContent(request.getContent());
        content.setDisplayOrder(request.getDisplayOrder());
        content.setActive(request.getActive());

        WebsiteContent savedContent =
                websiteContentRepository.save(content);

        logger.info("Website content created successfully with id: {}",
                savedContent.getId());

        return websiteContentMapper.toResponse(savedContent);
    }

    @Override
    public WebsiteContentResponse updateWebsiteContent(
            Long id,
            WebsiteContentRequest request) {

        logger.info("Updating website content with id: {}", id);

        WebsiteContent content =
                websiteContentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Website content not found with id: "
                                                + id));

        if (!content.getSectionKey().equals(request.getSectionKey())
                && websiteContentRepository.existsBySectionKey(
                request.getSectionKey())) {

            throw new DuplicateResourceException(
                    "Website content already exists for section: "
                            + request.getSectionKey());
        }

        content.setSectionKey(request.getSectionKey());
        content.setTitle(request.getTitle());
        content.setSubtitle(request.getSubtitle());
        content.setContent(request.getContent());
        content.setDisplayOrder(request.getDisplayOrder());
        content.setActive(request.getActive());

        WebsiteContent updatedContent =
                websiteContentRepository.save(content);

        logger.info("Website content updated successfully with id: {}",
                updatedContent.getId());

        return websiteContentMapper.toResponse(updatedContent);
    }

    @Override
    public WebsiteContentResponse getWebsiteContentById(Long id) {

        logger.info("Fetching website content with id: {}", id);

        WebsiteContent content =
                websiteContentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Website content not found with id: "
                                                + id));

        return websiteContentMapper.toResponse(content);
    }

    @Override
    public List<WebsiteContentResponse> getAllWebsiteContents() {

        logger.info("Fetching all website contents.");

        return websiteContentRepository
                .findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(websiteContentMapper::toResponse)
                .toList();
    }

    @Override
    public WebsiteContentResponse getBySection(
            WebsiteSection section) {

        logger.info("Fetching website content for section: {}",
                section);

        WebsiteContent content =
                websiteContentRepository.findBySectionKey(section)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Website content not found for section: "
                                                + section));

        return websiteContentMapper.toResponse(content);
    }

    @Override
    public void deleteWebsiteContent(Long id) {

        logger.info("Deleting website content with id: {}", id);

        WebsiteContent content =
                websiteContentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Website content not found with id: "
                                                + id));

        websiteContentRepository.delete(content);

        logger.info("Website content deleted successfully with id: {}",
                id);
    }
}