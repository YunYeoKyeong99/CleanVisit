package com.dabateam.cleanvisit.service;

import com.dabateam.cleanvisit.common.ReviewListType;
import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.entity.Review;
import com.dabateam.cleanvisit.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public void create(Review review){
        reviewMapper.create(review);
    }

    public List<Review> getReviewList (ReviewListType reviewListType,String userId, Long placeSeq, Long prevLastReviewSeq, Integer pageSize) {

        switch (reviewListType){
            case PLACE: return reviewMapper.findReviewList(null, placeSeq, prevLastReviewSeq, pageSize);
            case USER_ME: return reviewMapper.findReviewList(userId, null, prevLastReviewSeq, pageSize);
        }

        return Collections.emptyList();
    }

    public int getCountReviewList(Long placeSeq) {
        return reviewMapper.countReviewList(placeSeq);
    }

    public void delete(String userId, Long seq){
        reviewMapper.delete(userId, seq);
    }

}
