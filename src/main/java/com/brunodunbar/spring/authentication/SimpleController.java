package com.brunodunbar.spring.authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@Authentication(policy = ScopePolicy.ANY)
public class SimpleController {

    @GetMapping
    public Map<String, Object> test() {
        return Collections.singletonMap("status", "OK");
    }
}
