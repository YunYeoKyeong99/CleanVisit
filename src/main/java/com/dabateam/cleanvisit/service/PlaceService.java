package com.dabateam.cleanvisit.service;

import com.dabateam.cleanvisit.domain.entity.HygieneManagement;
import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.entity.PlaceLike;
import com.dabateam.cleanvisit.domain.entity.Quarantine;
import com.dabateam.cleanvisit.mapper.PlaceLikeMapper;
import com.dabateam.cleanvisit.mapper.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceMapper placeMapper;
    private final PlaceLikeMapper placeLikeMapper;

    private final HygieneManagementService hygieneManagementService;
    private final QuarantineService quarantineService;

    public List<Place> getPlaceList (Long prevLastPlaceSeq, Integer pageSize, String query) {

        return placeMapper.findPlaceList(prevLastPlaceSeq, pageSize, query);
    }

    public Place getPlace(Long seq){
        return placeMapper.findPlaceDetail(seq);
    }

    @Transactional
    public void createPlace(Place place, HygieneManagement hygieneManagement, Quarantine quarantine) {
        placeMapper.create(place);

        hygieneManagement.setPlaceSeq(place.getSeq());
        hygieneManagementService.createHygieneManagement(hygieneManagement);

        quarantine.setPlaceSeq(place.getSeq());
        quarantineService.createQuarantine(quarantine);
    }

    public Place getPlaceByUserId(String userId){
        return placeMapper.findPlaceByUserId(userId);
    }

    @Transactional
    public void createLike(String userId,Long seq){
         Place place = placeMapper.findBySeq(seq);

        if(place==null){
            //
        }

        PlaceLike placeLike = PlaceLike.builder()
                .userId(userId)
                .placeSeq(seq)
                .build();

        try{
            placeLikeMapper.create(placeLike);
        }catch(Exception e){
            // DuplicateKeyException
        }
    };
}
