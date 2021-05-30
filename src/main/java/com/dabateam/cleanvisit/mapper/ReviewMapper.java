package com.dabateam.cleanvisit.mapper;


import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.entity.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewMapper {
    int create(Review review);

    List<Review> findReviewList(
            @Param("userId") String userId,
            @Param("placeSeq") Long placeSeq,
            @Param("prevLastReviewSeq") Long prevLastReviewSeq,
            @Param("limitSize") Integer pageSize
    );

    int delete(
            @Param("userId") String userId,
            @Param("seq") Long seq
    );
}
