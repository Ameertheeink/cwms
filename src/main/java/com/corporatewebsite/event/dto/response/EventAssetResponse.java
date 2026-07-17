package com.corporatewebsite.event.dto.response;

import com.corporatewebsite.event.enums.FileType;

public class EventAssetResponse {

    private Long id;

    private String originalFileName;

    private String filePath;

    private FileType fileType;

    private Integer displayOrder;

    private Boolean isCover;

    public EventAssetResponse() {
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Boolean getIsCover() {
        return isCover;
    }

    public void setIsCover(Boolean cover) {
        isCover = cover;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

}