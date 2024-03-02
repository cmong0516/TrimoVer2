package com.example.demo.repository;

import com.example.demo.domain.entity.LikeReview;
import com.example.demo.domain.entity.QReviewPhoto;
import com.example.demo.domain.entity.QSpot;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.domain.entity.QLikeReview.likeReview;

@Repository
@RequiredArgsConstructor
public class LikeReviewRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<LikeReview> findLikeReviewByUserId(Long id) {
        return jpaQueryFactory.selectFrom(likeReview)
                .leftJoin(likeReview.users)
                .fetchJoin()
                .leftJoin(likeReview.review)
                .fetchJoin()
                .leftJoin(likeReview.review.spot, QSpot.spot)
                .fetchJoin()
                .leftJoin(likeReview.review.images, QReviewPhoto.reviewPhoto)
                .fetchJoin()
                .where(likeReview.users.id.eq(id))
                .fetch();
    }
}
