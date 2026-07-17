package com.corporatewebsite.event.service;

import com.corporatewebsite.event.dto.response.EventAssetResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventAssetService {

    /**
     * Upload one or more assets for an event.
     */
    List<EventAssetResponse> uploadAssets(
            Long eventId,
            List<MultipartFile> files);

    /**
     * Get all assets for an event.
     */
    List<EventAssetResponse> getAssetsByEventId(Long eventId);

    /**
     * Delete an asset.
     */
    void deleteAsset(Long eventId, Long assetId);

    void deleteAllAssets(Long eventId);

    void setCoverAsset(Long eventId,
                       Long assetId);


}