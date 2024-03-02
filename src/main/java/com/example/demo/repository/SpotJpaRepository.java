package com.example.demo.repository;

import com.example.demo.domain.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotJpaRepository extends JpaRepository<Spot ,Long> {
}
