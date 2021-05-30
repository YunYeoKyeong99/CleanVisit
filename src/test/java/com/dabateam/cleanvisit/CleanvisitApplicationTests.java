package com.dabateam.cleanvisit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class CleanvisitApplicationTests {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){

        String password = "pw";

        String enPw = passwordEncoder.encode(password);

        System.out.println("enPw: "+enPw);

        boolean matchResult = passwordEncoder.matches(password,enPw);

        System.out.println("matchResult: "+matchResult);
    }

    @Test
    void contextLoads() {
    }

}
