package com.dabateam.cleanvisit.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HygieneManagement {

    private Long seq;

    private Long placeSeq;

    private Boolean handSanitization;

    private Boolean heatCheck;

    private Boolean wearingMask;

    private String distance;
}
