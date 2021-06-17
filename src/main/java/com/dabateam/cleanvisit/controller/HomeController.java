package com.dabateam.cleanvisit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public void homePage(){
        log.info("registerForm: access to member");
    }

}
