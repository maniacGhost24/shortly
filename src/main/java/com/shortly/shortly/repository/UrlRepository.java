package com.shortly.shortly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shortly.shortly.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long>{
    Optional<Url> findByShortCode(String shortCode);    
}
