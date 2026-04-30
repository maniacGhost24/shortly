package com.shortly.shortly.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shortly.shortly.dto.StatsResponse;
import com.shortly.shortly.exception.UrlNotFoundException;
import com.shortly.shortly.model.Url;
import com.shortly.shortly.repository.UrlRepository;

@Service
public class UrlService {

    private final UrlRepository repo;

    // Add a basic cache.
    private final Map<String, String> cache = new ConcurrentHashMap<>();

    public UrlService(UrlRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public String shortenUrl(String originalUrl) {

        // Step 1: save without shortcode
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setClickCount(0L);

        repo.save(url); // Database assigns ID

        // Step 2: generate Short code from ID
        String shortCode = encodeBase62(url.getId());

        // Step 3: update entity
        url.setShortCode(shortCode);
        // repo.save(url);

        return shortCode;
    }

    @Transactional
    public String getOriginalUrl(String shortCode) {

        if (cache.containsKey(shortCode)){
            return cache.get(shortCode);
        }

        Url url = repo.findByShortCode(shortCode).orElseThrow(() -> new UrlNotFoundException("URL NOT FOUND!!"));

        cache.put(shortCode, url.getOriginalUrl());

        url.setClickCount(url.getClickCount() + 1);

        url.setLastAccessed(LocalDateTime.now());
        // repo.save(url);

        return url.getOriginalUrl();
    }

    // private String generateShortCode(){
    // String code;

    // do{
    // code = randomString();
    // }while (repo.findByShortCode(code).isPresent());

    // return code;
    // }

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String encodeBase62(long num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            sb.append(BASE62.charAt((int) (num % 62)));
            num /= 62;
        }

        return sb.reverse().toString();
    }

    public StatsResponse getStats(String shortCode) {
        Url url = repo.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("URL not found"));

        return new StatsResponse(
                url.getOriginalUrl(),
                url.getShortCode(),
                url.getClickCount(),
                url.getCreatedAt(),
                url.getLastAccessed());
    }
}
