package com.dabateam.cleanvisit.domain.entity;

import com.dabateam.cleanvisit.service.UploadService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.Optional;

@Getter
@Setter
@ToString
public class Place {

    private Long seq;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private String imgUrl;

    private Integer category;

    private String adminId;

    private Double score;

    HygieneManagement hygieneManagement;

    Quarantine quarantine;

    private Double reviewAvgScore;

    private Integer reviewCount;

    private Boolean isLike;

    public Integer getReviewAvgScoreInt() {
        if(reviewAvgScore != null) {
            return reviewAvgScore.intValue();
        }
        return null;
        // return Optional.ofNullable(reviewAvgScore).map(Double::intValue).orElse(null);
    }
}
