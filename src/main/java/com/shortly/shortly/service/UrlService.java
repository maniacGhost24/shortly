package com.shortly.shortly.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.shortly.shortly.model.Url;
import com.shortly.shortly.repository.UrlRepository;

@Service
public class UrlService {
    
    private final UrlRepository repo;

    public UrlService(UrlRepository repo){
        this.repo = repo;
    }

    public String shortenUrl(String originalUrl){
        
        String shortCode = generateShortCode();

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        url.setClickCount(0L);

        repo.save(url);

        return shortCode;
    }

    public String getOriginalUrl(String shortCode){
        Url url = repo.findByShortCode(shortCode).orElseThrow(() -> new RuntimeException("URL NOT FOUND!!"));

        url.setClickCount(url.getClickCount() + 1);
        repo.save(url);

        return url.getOriginalUrl();
    }

    private String generateShortCode(){
        String code;

        do{
            code = randomString();
        }while (repo.findByShortCode(code).isPresent());

        return code;
    }

    private String randomString(){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i=0;i<6;i++){
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }
}
