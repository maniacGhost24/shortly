package com.shortly.shortly.dto;

import java.time.LocalDateTime;

public class StatsResponse {
    
    private String originalUrl;
    private String shortCode;
    private Long clickCount;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccessed;

    public StatsResponse(String originalUrl, String shortCode, Long clickCount, LocalDateTime createdAt, LocalDateTime lastAccessed){
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.clickCount = clickCount;
        this.createdAt = createdAt;
        this.lastAccessed = lastAccessed;
    }

    public String getOriginalUrl() { return originalUrl; }
    public String getShortCode() { return shortCode; }
    public Long getClickCount() { return clickCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastAccessed() { return lastAccessed; }
}
