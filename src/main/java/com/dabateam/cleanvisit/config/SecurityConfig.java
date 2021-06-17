package com.dabateam.cleanvisit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config ...");

        // 폼 로그인 기능과 베이직 인증 비활성화
//         http.formLogin().disable()
//                 .httpBasic().disable();



         // CSRF 방지 지원 기능 비활성화
         http.csrf().disable();

         // URL 패턴으로 접근 제한을 설정한다.
        http.authorizeRequests()
                .antMatchers("/places/register", "/users/my-page")
                .hasRole("USER");

        http.authorizeRequests()
//                .antMatchers("/users", "/places/list")
                .anyRequest()
                .permitAll();


        //인가/인증에 문제시 로그인 화면
        http.formLogin()
                .defaultSuccessUrl("/home",true);
        http.csrf().disable();
        http.logout();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
