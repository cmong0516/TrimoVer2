package com.example.demo.repository;

import com.example.demo.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewJpaRepository extends JpaRepository<Review,Long> {
}
