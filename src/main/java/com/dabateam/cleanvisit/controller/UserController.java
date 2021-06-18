package com.dabateam.cleanvisit.controller;

import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.entity.User;
import com.dabateam.cleanvisit.domain.req.ReqUserUpdate;
import com.dabateam.cleanvisit.domain.req.ResUploadResult;
import com.dabateam.cleanvisit.resolver.SessionUser;
import com.dabateam.cleanvisit.service.PlaceService;
import com.dabateam.cleanvisit.service.UploadService;
import com.dabateam.cleanvisit.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PlaceService placeService;
    private final UploadService uploadService;

    // 비밀번호 암호처리기
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public void join() {
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        log.info("user.getUserName() = "+user.getName());

        // 비밀번호 암호화
        String inputPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(inputPassword));

        userService.register(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> modify(
            @SessionUser String userId,
            @RequestBody ReqUserUpdate req
    ){
        User user = new User();

        user.setId(userId);

        // 비밀번호 암호화
        if(StringUtils.hasLength(req.getPassword())) {
            String inputPassword = req.getPassword();
            user.setPassword(passwordEncoder.encode(inputPassword));
        }
        if(StringUtils.hasLength(req.getAddress())) {
            user.setAddress(req.getAddress());
        }

        userService.modify(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<User> delete(
            @SessionUser String userId
    ){
        User user = new User();

        user.setId(userId);

        userService.delete(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/my-page")
    public void myPage(
            @SessionUser String userId,
            Model model
    ) {
        Place place = placeService.getPlaceByUserId(userId);
        model.addAttribute("place", place);
    }

    @GetMapping("/modify")
    public void modify(
            @SessionUser String userId
    ){

    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Void> uploadImages(
            @PathVariable String userId,
            MultipartFile uploadFile
    ) {
        ResUploadResult resUploadResult = uploadService.uploadFile(uploadFile);

        userService.updateImage(userId, resUploadResult.getImageURL());

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/my-page-like")
    public void myPageLike(){
    }

    @GetMapping("/my-page-review")
    public void myPageReview(){
    }

}
