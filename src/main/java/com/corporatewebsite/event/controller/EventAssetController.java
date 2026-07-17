package com.corporatewebsite.event.controller;

import com.corporatewebsite.common.response.ApiResponse;
import com.corporatewebsite.event.dto.response.EventAssetResponse;
import com.corporatewebsite.event.service.EventAssetService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class EventAssetController {

    private static final Logger logger =
            LoggerFactory.getLogger(EventAssetController.class);

    private final EventAssetService eventAssetService;

    public EventAssetController(EventAssetService eventAssetService) {
        this.eventAssetService = eventAssetService;
    }

    /**
     * Upload one or more assets for an event.
     */
    @PostMapping("/events/{eventId}/assets")
    public ResponseEntity<ApiResponse<List<EventAssetResponse>>> uploadAssets(
            @PathVariable @Positive Long eventId,
            @RequestParam("files") List<MultipartFile> files) {

        logger.info("Received upload request for {} file(s) for event id: {}",
                files.size(), eventId);

        List<EventAssetResponse> response =
                eventAssetService.uploadAssets(eventId, files);

        logger.info("Successfully uploaded {} file(s) for event id: {}",
                response.size(), eventId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Asset(s) uploaded successfully",
                        response));
    }

    /**
     * Fetch all assets for an event.
     */
    @GetMapping("/events/{eventId}/assets")
    public ResponseEntity<ApiResponse<List<EventAssetResponse>>> getAssetsByEventId(
            @PathVariable @Positive Long eventId) {

        logger.info("Received request to fetch assets for event id: {}", eventId);

        List<EventAssetResponse> response =
                eventAssetService.getAssetsByEventId(eventId);

        logger.info("Fetched {} asset(s) for event id: {}",
                response.size(), eventId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Assets fetched successfully",
                        response));
    }

    /**
     * Delete an asset.
     */
    @DeleteMapping("/events/{eventId}/assets/{assetId}")
    public ResponseEntity<ApiResponse<Void>> deleteAsset(
            @PathVariable Long eventId,
            @PathVariable Long assetId) {

        logger.info("DELETE /api/events/{}/assets/{}", eventId, assetId);

        eventAssetService.deleteAsset(eventId, assetId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Asset deleted successfully",
                        null));
    }

    @DeleteMapping("/events/{eventId}/assets")
    public ResponseEntity<ApiResponse<Void>> deleteAllAssets(
            @PathVariable Long eventId) {

        logger.info("DELETE /api/events/{}/assets", eventId);

        eventAssetService.deleteAllAssets(eventId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "All assets deleted successfully",
                        null));
    }

    @PutMapping("/api/events/{eventId}/assets/{assetId}/cover")
    public ResponseEntity<ApiResponse<Void>> setCoverAsset(
            @PathVariable Long eventId,
            @PathVariable Long assetId) {

        logger.info(
                "PUT /api/events/{}/assets/{}/cover",
                eventId,
                assetId);

        eventAssetService.setCoverAsset(eventId, assetId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Cover image updated successfully",
                        null));
    }

}