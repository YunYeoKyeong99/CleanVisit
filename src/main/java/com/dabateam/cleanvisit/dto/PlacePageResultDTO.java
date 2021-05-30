package com.dabateam.cleanvisit.dto;

import com.dabateam.cleanvisit.domain.entity.Place;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PlacePageResultDTO {

    private List<Place> placeList;

    //총 페이지 번호
    private int totalPage;

    //현재 페이지 번호
    private int page;
    //목록 사이즈
    private int size;
}
