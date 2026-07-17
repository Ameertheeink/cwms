package com.corporatewebsite.event.mapper;

import com.corporatewebsite.event.dto.response.EventAssetResponse;
import com.corporatewebsite.event.entity.EventAsset;
import org.springframework.stereotype.Component;

@Component
public class EventAssetMapper {

    public EventAssetResponse toResponse(EventAsset asset) {

        if (asset == null) {
            return null;
        }

        EventAssetResponse response = new EventAssetResponse();

        response.setId(asset.getId());
        response.setOriginalFileName(asset.getOriginalFileName());
        response.setFilePath(asset.getFilePath());
        response.setFileType(asset.getFileType());
        response.setIsCover(asset.getIsCover());
        response.setDisplayOrder(asset.getDisplayOrder());

        return response;
    }

}