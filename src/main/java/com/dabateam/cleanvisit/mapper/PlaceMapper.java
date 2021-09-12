package com.dabateam.cleanvisit.mapper;

import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.mappedenum.PlaceCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlaceMapper {

    int create(Place place);

    List<Place> findPlaceList(
            //@Param("userSeq") Integer userSeq,
            @Param("prevLastPlaceSeq") Long prevLastPlaceSeq,
            @Param("limitSize") Integer pageSize,
            @Param("query") String query,
            @Param("category") Integer category
    );

    Place findPlaceByUserId(
            @Param("userId") String userId
    );

    Place findBySeq(
            @Param("seq") Long seq
    );

    Place findPlaceDetail(
            @Param("seq") Long seq,
            @Param("userId") String userId
    );

    int updatePlaceImageUrl(
            @Param("seq") Long seq,
            @Param("imgUrl") String imgUrl
    );
}
