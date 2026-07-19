package com.corporatewebsite.websitecontent.repository;

import com.corporatewebsite.websitecontent.entity.WebsiteContent;
import com.corporatewebsite.websitecontent.enums.WebsiteSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebsiteContentRepository
        extends JpaRepository<WebsiteContent, Long> {

    List<WebsiteContent> findAllByOrderByDisplayOrderAsc();

    Optional<WebsiteContent> findBySectionKey(WebsiteSection sectionKey);

    boolean existsBySectionKey(WebsiteSection sectionKey);
}