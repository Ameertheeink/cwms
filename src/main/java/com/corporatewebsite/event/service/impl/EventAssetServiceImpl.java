package com.corporatewebsite.event.service.impl;

import com.corporatewebsite.common.exception.ResourceNotFoundException;
import com.corporatewebsite.common.file.dto.FileUploadResponse;
import com.corporatewebsite.common.file.service.FileStorageService;
import com.corporatewebsite.event.dto.response.EventAssetResponse;
import com.corporatewebsite.event.entity.Event;
import com.corporatewebsite.event.entity.EventAsset;
import com.corporatewebsite.event.enums.FileType;
import com.corporatewebsite.event.mapper.EventAssetMapper;
import com.corporatewebsite.event.repository.EventAssetRepository;
import com.corporatewebsite.event.repository.EventRepository;
import com.corporatewebsite.event.service.EventAssetService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EventAssetServiceImpl implements EventAssetService {

    private static final Logger logger =
            LoggerFactory.getLogger(EventAssetServiceImpl.class);

    private final EventRepository eventRepository;
    private final EventAssetRepository eventAssetRepository;
    private final FileStorageService fileStorageService;
    private final EventAssetMapper eventAssetMapper;

    public EventAssetServiceImpl(EventRepository eventRepository,
                                 EventAssetRepository eventAssetRepository,
                                 FileStorageService fileStorageService,
                                 EventAssetMapper eventAssetMapper) {

        this.eventRepository = eventRepository;
        this.eventAssetRepository = eventAssetRepository;
        this.fileStorageService = fileStorageService;
        this.eventAssetMapper = eventAssetMapper;
    }

    @Override
    public List<EventAssetResponse> uploadAssets(Long eventId,
                                                 List<MultipartFile> files) {

        logger.info("Uploading {} asset(s) for event id: {}",
                files.size(), eventId);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event not found with id: " + eventId));

        List<EventAsset> existingAssets =
                eventAssetRepository.findByEventIdOrderByDisplayOrderAsc(eventId);

        int displayOrder = existingAssets.size() + 1;

        List<EventAssetResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {

            try {

                FileUploadResponse uploadResponse =
                        fileStorageService.storeFile(file, "events");

                EventAsset asset = new EventAsset();

                asset.setEvent(event);
                asset.setOriginalFileName(uploadResponse.getOriginalFileName());
                asset.setStoredFileName(uploadResponse.getStoredFileName());
                asset.setFilePath(uploadResponse.getFilePath());

// ⭐ Missing fields
                asset.setFileSize(uploadResponse.getFileSize());
                asset.setContentType(uploadResponse.getContentType());

                asset.setFileType(determineFileType(file));
                asset.setDisplayOrder(displayOrder++);
                asset.setIsCover(false);

                EventAsset savedAsset =
                        eventAssetRepository.save(asset);

                responses.add(eventAssetMapper.toResponse(savedAsset));

                logger.info("Uploaded asset '{}' for event {}",
                        uploadResponse.getOriginalFileName(),
                        eventId);

            } catch (IOException ex) {

                logger.error("Failed to upload file: {}",
                        file.getOriginalFilename(), ex);

                throw new RuntimeException(
                        "Failed to upload file: "
                                + file.getOriginalFilename(), ex);
            }
        }

        logger.info("Successfully uploaded {} asset(s) for event {}",
                responses.size(), eventId);

        return responses;
    }

    @Override
    public List<EventAssetResponse> getAssetsByEventId(Long eventId) {

        logger.info("Fetching assets for event id: {}", eventId);

        List<EventAsset> assets =
                eventAssetRepository.findByEventIdOrderByDisplayOrderAsc(eventId);

        return assets.stream()
                .map(eventAssetMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteAsset(Long eventId, Long assetId) {

        logger.info("Deleting asset {} for event {}", assetId, eventId);

        // Verify event exists
        eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event not found with id: " + eventId));

        // Verify asset belongs to event
        EventAsset asset = eventAssetRepository
                .findByIdAndEventId(assetId, eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Asset not found with id: " + assetId +
                                        " for event id: " + eventId));

        // Delete physical file
        try {
            fileStorageService.deleteFile(asset.getFilePath());
        } catch (IOException ex) {
            logger.error("Unable to delete physical file: {}",
                    asset.getFilePath(), ex);

            throw new RuntimeException("Unable to delete physical file.", ex);
        }

        // Delete DB record
        eventAssetRepository.delete(asset);

        logger.info("Asset {} deleted successfully", assetId);
    }

    @Override
    public void deleteAllAssets(Long eventId) {

        logger.info("Deleting all assets for event {}", eventId);

        // Verify event exists
        eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event not found with id: " + eventId));

        List<EventAsset> assets =
                eventAssetRepository.findByEventIdOrderByDisplayOrderAsc(eventId);

        for (EventAsset asset : assets) {

            try {

                fileStorageService.deleteFile(asset.getFilePath());

            } catch (IOException ex) {

                logger.error("Unable to delete file {}",
                        asset.getFilePath(), ex);

                throw new RuntimeException(
                        "Unable to delete physical file.", ex);
            }

            eventAssetRepository.delete(asset);
        }

        logger.info("Deleted {} asset(s) for event {}",
                assets.size(), eventId);
    }

    /**
     * Determines file type based on content type.
     */
    private FileType determineFileType(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType != null) {
            if (contentType.startsWith("image")) {
                return FileType.IMAGE;
            }

            if (contentType.startsWith("video")) {
                return FileType.VIDEO;
            }
        }

        String fileName = file.getOriginalFilename();

        if (fileName != null) {
            String lower = fileName.toLowerCase();

            if (lower.endsWith(".jpg") ||
                    lower.endsWith(".jpeg") ||
                    lower.endsWith(".png") ||
                    lower.endsWith(".gif") ||
                    lower.endsWith(".webp")) {
                return FileType.IMAGE;
            }

            if (lower.endsWith(".mp4") ||
                    lower.endsWith(".avi") ||
                    lower.endsWith(".mov")) {
                return FileType.VIDEO;
            }
        }

        return FileType.DOCUMENT;
    }
    @Override
    public void setCoverAsset(Long eventId,
                              Long assetId) {

        logger.info("Setting asset {} as cover for event {}",
                assetId,
                eventId);

        // Verify event exists
        eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event not found with id: " + eventId));

        // Verify asset belongs to event
        EventAsset selectedAsset =
                eventAssetRepository
                        .findByIdAndEventId(assetId, eventId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Asset not found with id: "
                                                + assetId));

        // Fetch all assets of the event
        List<EventAsset> assets =
                eventAssetRepository.findByEventId(eventId);

        // Remove previous cover
        for (EventAsset asset : assets) {

            asset.setIsCover(false);
        }

        // Set selected asset as cover
        selectedAsset.setIsCover(true);

        // Save all changes
        eventAssetRepository.saveAll(assets);

        logger.info("Asset {} is now cover for event {}",
                assetId,
                eventId);
    }
}