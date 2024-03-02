package com.example.demo.repository;

import com.example.demo.domain.entity.QReviewPhoto;
import com.example.demo.domain.entity.QSpot;
import com.example.demo.domain.entity.QUsers;
import com.example.demo.domain.entity.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.demo.domain.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Review getReviewById(Long id) {

        return jpaQueryFactory.selectFrom(review)
                .leftJoin(review.users, QUsers.users)
                .fetchJoin()
                .leftJoin(review.spot, QSpot.spot)
                .fetchJoin()
                .leftJoin(review.images, QReviewPhoto.reviewPhoto)
                .fetchJoin()
                .where(review.id.eq(id))
                .fetchOne();

    }
}
