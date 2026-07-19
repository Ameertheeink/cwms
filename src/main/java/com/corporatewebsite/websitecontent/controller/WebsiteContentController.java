package com.corporatewebsite.websitecontent.controller;

import com.corporatewebsite.common.response.ApiResponse;
import com.corporatewebsite.websitecontent.dto.request.WebsiteContentRequest;
import com.corporatewebsite.websitecontent.dto.response.WebsiteContentResponse;
import com.corporatewebsite.websitecontent.enums.WebsiteSection;
import com.corporatewebsite.websitecontent.service.WebsiteContentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/website-content")
public class WebsiteContentController {

    private final WebsiteContentService websiteContentService;

    public WebsiteContentController(
            WebsiteContentService websiteContentService) {

        this.websiteContentService = websiteContentService;
    }

    /**
     * Create Website Content
     */
    @PostMapping
    public ResponseEntity<ApiResponse<WebsiteContentResponse>>
    createWebsiteContent(
            @Valid @RequestBody WebsiteContentRequest request) {

        WebsiteContentResponse response =
                websiteContentService.createWebsiteContent(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Website content created successfully",
                        response));
    }

    /**
     * Get All Website Contents
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<WebsiteContentResponse>>>
    getAllWebsiteContents() {

        List<WebsiteContentResponse> response =
                websiteContentService.getAllWebsiteContents();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Website contents fetched successfully",
                        response));
    }

    /**
     * Get Website Content By Id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WebsiteContentResponse>>
    getWebsiteContentById(
            @PathVariable Long id) {

        WebsiteContentResponse response =
                websiteContentService.getWebsiteContentById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Website content fetched successfully",
                        response));
    }

    /**
     * Get Website Content By Section
     */
    @GetMapping("/section/{section}")
    public ResponseEntity<ApiResponse<WebsiteContentResponse>>
    getBySection(
            @PathVariable WebsiteSection section) {

        WebsiteContentResponse response =
                websiteContentService.getBySection(section);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Website content fetched successfully",
                        response));
    }

    /**
     * Update Website Content
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WebsiteContentResponse>>
    updateWebsiteContent(
            @PathVariable Long id,
            @Valid @RequestBody WebsiteContentRequest request) {

        WebsiteContentResponse response =
                websiteContentService.updateWebsiteContent(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Website content updated successfully",
                        response));
    }

    /**
     * Delete Website Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>
    deleteWebsiteContent(
            @PathVariable Long id) {

        websiteContentService.deleteWebsiteContent(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Website content deleted successfully",
                        null));
    }

}