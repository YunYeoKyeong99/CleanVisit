package com.dabateam.cleanvisit.controller;

import com.dabateam.cleanvisit.common.ReviewListType;
import com.dabateam.cleanvisit.domain.entity.Review;
import com.dabateam.cleanvisit.resolver.SessionUser;
import com.dabateam.cleanvisit.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews/list")
    public void getReviews(
            @SessionUser String userId,
            @RequestParam("type") ReviewListType reviewListType,
            @Valid @Positive @RequestParam(value="place_seq", required = false) Long placeSeq,
            @Valid @Positive @RequestParam(value="prev_last_review_seq", required = false) Long prevLastReviewSeq,
            @Valid @Positive @Max(50L) @RequestParam(value = "page_size", required = false, defaultValue = "10") int pageSize,
            Model model
    ) {
         List<Review> reviewList = reviewService.getReviewList(reviewListType, userId, placeSeq, prevLastReviewSeq, pageSize);
         model.addAttribute("reviewList", reviewList);

         if(placeSeq != null) {
             Integer reviewCount = reviewService.getCountReviewList(placeSeq);
             model.addAttribute("reviewCount", reviewCount);
         }
    }

    @GetMapping("/reviews/register")
    public void registerForm(){
        log.info("registerForm: access to member");
    }

    @PostMapping(value = "/places/{placeSeq}/reviews",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(
            @SessionUser String userId,
            @PathVariable Long placeSeq,
            @Valid @RequestBody Review review
    ){
        review.setUserId(userId);

        review.setPlaceSeq(placeSeq);

        reviewService.create(review);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/reviews/{reviewSeq}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(
            @SessionUser String userId,
            @PathVariable Long reviewSeq
    ){
        reviewService.delete(userId, reviewSeq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
