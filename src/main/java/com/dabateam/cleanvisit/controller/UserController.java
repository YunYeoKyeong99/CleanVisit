package com.dabateam.cleanvisit.controller;

import com.dabateam.cleanvisit.domain.entity.Place;
import com.dabateam.cleanvisit.domain.entity.User;
import com.dabateam.cleanvisit.resolver.SessionUser;
import com.dabateam.cleanvisit.service.PlaceService;
import com.dabateam.cleanvisit.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PlaceService placeService;

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

    @GetMapping("/my-page")
    public void myPage(
            @SessionUser String userId,
            Model model
    ) {
        Place place = placeService.getPlaceByUserId(userId);
        model.addAttribute("place", place);
    }

    @GetMapping("/modify")
    public void modify(){
    }

//    @GetMapping()
//    public ResponseEntity<User> re
}
