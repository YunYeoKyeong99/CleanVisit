package com.dabateam.cleanvisit.controller;

import com.dabateam.cleanvisit.domain.entity.HygieneManagement;
import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.entity.Quarantine;
import com.dabateam.cleanvisit.domain.entity.User;
import com.dabateam.cleanvisit.domain.req.ReqPlaceCreate;
import com.dabateam.cleanvisit.domain.req.ResUploadResult;
import com.dabateam.cleanvisit.resolver.SessionUser;
import com.dabateam.cleanvisit.service.HygieneManagementService;
import com.dabateam.cleanvisit.service.PlaceService;
import com.dabateam.cleanvisit.service.QuarantineService;
import com.dabateam.cleanvisit.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequestMapping("/places")
@Controller
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;
    private final UploadService uploadService;

    @GetMapping("/list")
    public void getPlaces(
            @SessionUser String userId,
            @Valid @Min(1L) @RequestParam(value = "prev_last_place_seq", required = false) Long prevLastPlaceSeq,
            @Valid @Min(1L) @Max(50L) @RequestParam(value = "page_size", required = false, defaultValue = "10") int pageSize,
            @Valid @NotBlank @RequestParam(value = "query", required = false) String query,
            Model model
    ){
        List<Place> placeList = placeService.getPlaceList(
                //user.getSeq(),
                prevLastPlaceSeq,
                pageSize,
                query);
        model.addAttribute("placeList", placeList);
    }

    @GetMapping("/detail")
    public void getPlaceDetail(
            @SessionUser String userId,
            @Valid @RequestParam("place_seq") Long seq,
            Model model
    ){
        Place place = placeService.getPlace(seq);
        model.addAttribute("place", place);

        if(place.getQuarantine() != null) {
            LocalDateTime quarantineDatetime = place.getQuarantine().getDatetime();
            model.addAttribute("quarantine_date_string",
                    quarantineDatetime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh:mm"))); // 2021년 5월 23일 AM 8:00
        }
    }

    @GetMapping("/my-page")
    public void myPage(
            @SessionUser String userId,
            Model model
    ) {
        Place place = placeService.getPlaceByUserId(userId);
        model.addAttribute("place", place);
    }

    @GetMapping("/register")
    public void registerForm(){
        log.info("registerForm: access to member");
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Place register(
            @SessionUser String userId,
            @Valid @RequestBody ReqPlaceCreate reqPlaceCreate
    ) {
        Place place = reqPlaceCreate.getPlace();
        HygieneManagement hygieneManagement = reqPlaceCreate.getHygieneManagement();
        Quarantine quarantine = reqPlaceCreate.getQuarantine();

        place.setAdminId(userId);

        placeService.createPlace(place, hygieneManagement, quarantine);

        return place;
    }

    @PutMapping("/{seq}/image")
    public ResponseEntity<Void> uploadImages(
            @SessionUser String userId,
            @PathVariable Long seq,
            MultipartFile uploadFile
    ) {
        ResUploadResult resUploadResult = uploadService.uploadFile(uploadFile);

        placeService.updateImage(userId, seq, resUploadResult.getImageURL());

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/v1/products/{seq}/likes")
//    public ResponseEntity<Void> createProductLike(
//            @ApiIgnore @SessionUser User user,
//            @PathVariable Long seq
//    ){
//        productService.createLike(user,seq);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @PostMapping(value="/{seq}/likes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPlaceLike(
            @SessionUser String userId,
            @PathVariable Long seq
    ){
        placeService.createLike(userId,seq);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
