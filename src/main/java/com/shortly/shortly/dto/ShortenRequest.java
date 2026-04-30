package com.shortly.shortly.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ShortenRequest {

    @NotBlank(message = "URL cannot be empty")
    @Pattern(regexp = "https?://.*", message = "Invalid URL format")
    private String url;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
