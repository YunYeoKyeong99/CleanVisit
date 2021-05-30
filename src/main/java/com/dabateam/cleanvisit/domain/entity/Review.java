package com.dabateam.cleanvisit.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Review {
    private Long seq;

    private String userId;

    private Long placeSeq;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Double score;

    //date
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;

    private User user;
}
