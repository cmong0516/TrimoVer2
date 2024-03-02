package com.example.demo.repository;

import com.example.demo.domain.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewPhotoJpaRepository extends JpaRepository<ReviewPhoto,Long> {
}
