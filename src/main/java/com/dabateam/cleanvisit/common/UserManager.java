package com.dabateam.cleanvisit.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class UserManager {
    public final static GrantedAuthority USER_AUTHORITY = new SimpleGrantedAuthority("ROLE_USER");
    public final static Collection<GrantedAuthority> USER_AUTHORITIES = Collections.singleton(USER_AUTHORITY);

    public static boolean isLogin(Authentication auth) {
        return auth != null && auth.isAuthenticated() && auth.getAuthorities().contains(USER_AUTHORITY);
    }

    public static String getSessionUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!isLogin(auth)) {
            return null;
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
