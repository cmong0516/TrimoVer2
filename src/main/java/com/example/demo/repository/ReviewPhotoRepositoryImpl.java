package com.example.demo.repository;

import com.example.demo.domain.entity.ReviewPhoto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.domain.entity.QReviewPhoto.reviewPhoto;

@RequiredArgsConstructor
@Repository
public class ReviewPhotoRepositoryImpl implements ReviewPhotoRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ReviewPhoto> findReviewPhotoByReviewIdInUrls(Long id, List<String> urls) {
        return jpaQueryFactory.selectFrom(reviewPhoto)
                .where(reviewPhoto.review.id.eq(id)
                        .and(reviewPhoto.url.in(urls)))
                .fetch();
    }
}
