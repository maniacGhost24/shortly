package com.shortly.shortly.controller;

import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shortly.shortly.dto.ShortenReponse;
import com.shortly.shortly.dto.ShortenRequest;
import com.shortly.shortly.dto.StatsResponse;
import com.shortly.shortly.service.UrlService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/api")
public class UrlController {
    
    private final UrlService service;

    @Value("${app.base-url}")
    private String baseUrl;

    public UrlController(UrlService service){
        this.service = service;
    }

    @PostMapping("/shorten")
    public ShortenReponse shorten(@Valid @RequestBody ShortenRequest request){
        String code = service.shortenUrl(request.getUrl());
        return new ShortenReponse(baseUrl+"/api/" + code);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode){
        String originalUrl = service.getOriginalUrl(shortCode);

        return ResponseEntity.status(302).header(HttpHeaders.LOCATION, originalUrl).build();
    }

    @GetMapping("/stats/{shortCode}")
    public StatsResponse getStats(@PathVariable String shortCode){
        return service.getStats(shortCode);
    }
}
