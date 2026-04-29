package com.shortly.shortly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shortly.shortly.service.UrlService;

@RestController
@RequestMapping("/api")
public class UrlController {
    
    private final UrlService service;

    public UrlController(UrlService service){
        this.service = service;
    }

    @PostMapping("/shorten")
    public String shorten(@RequestBody String url){
        String code = service.shortenUrl(url);
        return "http://localhost:8080/api/"+code;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode){
        String originalUrl = service.getOriginalUrl(shortCode);

        return ResponseEntity.status(302).header("Location", originalUrl).build();
    }
}
