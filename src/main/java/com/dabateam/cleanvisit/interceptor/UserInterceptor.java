package com.dabateam.cleanvisit.interceptor;

import com.dabateam.cleanvisit.common.UserManager;
import com.dabateam.cleanvisit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class UserInterceptor extends HandlerInterceptorAdapter {
    private final UserService userService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        if(modelAndView != null) {
            String userId = UserManager.getSessionUserId();
            if (userId != null) {
                modelAndView.addObject("user", userService.getUser(userId));
            }
        }
    }
}
