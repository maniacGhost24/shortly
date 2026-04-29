package com.shortly.shortly.dto;

public class ShortenReponse {
    private String shortUrl;

    public ShortenReponse(String shortUrl){
        this.shortUrl = shortUrl;
    }

    public String getShortUrl(){
        return shortUrl;
    }
}
