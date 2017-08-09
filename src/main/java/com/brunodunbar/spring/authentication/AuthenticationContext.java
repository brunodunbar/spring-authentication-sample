package com.brunodunbar.spring.authentication;

public class AuthenticationContext {

    private ScopePolicy scopePolicy;

    public AuthenticationContext(ScopePolicy scopePolicy) {
        this.scopePolicy = scopePolicy;
    }

    public ScopePolicy getScopePolicy() {
        return scopePolicy;
    }

    @Override
    public String toString() {
        return "AuthenticationContext{" +
                "scopePolicy=" + scopePolicy +
                '}';
    }
}
