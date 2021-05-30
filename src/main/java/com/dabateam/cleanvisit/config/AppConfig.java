package com.dabateam.cleanvisit.config;

import com.dabateam.cleanvisit.interceptor.UserInterceptor;
import com.dabateam.cleanvisit.resolver.SessionUserArgResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class AppConfig extends WebMvcConfigurationSupport {
    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        super.addArgumentResolvers(resolvers);

        resolvers.add(new SessionUserArgResolver());
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        registry
                .addInterceptor(userInterceptor)
                .addPathPatterns("/**");
//                .excludePathPatterns("/join", "/login", "/logout")
    }
}