package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewPhoto extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String url;
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public ReviewPhoto(MultipartFile multipartFile , String url) {
        this.fileName = multipartFile.getOriginalFilename();
        this.url = url;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
