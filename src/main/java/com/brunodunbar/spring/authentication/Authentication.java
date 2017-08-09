package com.brunodunbar.spring.authentication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Authentication {

    ScopePolicy policy() default ScopePolicy.ALL;

}
