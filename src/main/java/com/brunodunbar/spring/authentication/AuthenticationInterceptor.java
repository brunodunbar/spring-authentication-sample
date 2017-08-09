package com.brunodunbar.spring.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    private final AuthenticationContext defaultAuthenticationContext;

    @Autowired
    public AuthenticationInterceptor(AuthenticationContext defaultAuthenticationContext) {
        this.defaultAuthenticationContext = defaultAuthenticationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        Authentication annotation = Optional.ofNullable(AnnotationUtils.findAnnotation(method, Authentication.class))
                .orElseGet(() -> AnnotationUtils.findAnnotation(method.getDeclaringClass(), Authentication.class));

        AuthenticationContext context = defaultAuthenticationContext;
        if(annotation != null) {
            AnnotationAttributes attributes = AnnotationUtils.getAnnotationAttributes(method, annotation);
            ScopePolicy policy = attributes.getEnum("policy");

            context = new AuthenticationContext(policy);
        }

        LOGGER.debug(context.toString());


        return super.preHandle(request, response, handler);
    }

}
