package com.dabateam.cleanvisit.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceLike {

    private Long seq;

    private String userId;

    private Long placeSeq;
}
