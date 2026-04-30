package com.shortly.shortly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
// import lombok.Setter;
// import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String originalUrl;

    @Column(unique = true)
    private String shortCode;

    private long clickCount;

    private LocalDateTime createdAt;
    private LocalDateTime lastAccessed;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    // @PreUpdate
    // public void onUpdated(){
    //     this.lastAccessed = LocalDateTime.now();
    // }
}
