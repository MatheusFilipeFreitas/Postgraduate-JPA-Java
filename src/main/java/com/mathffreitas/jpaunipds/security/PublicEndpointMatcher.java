package com.mathffreitas.jpaunipds.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

public final class PublicEndpointMatcher {

    private static final List<RequestMatcher> MATCHERS = List.of(
            PathPatternRequestMatcher.pathPattern(HttpMethod.POST, "/auth/register"),
            PathPatternRequestMatcher.pathPattern(HttpMethod.POST, "/auth/login"),
            PathPatternRequestMatcher.pathPattern(HttpMethod.GET, "/swagger-ui/**"),
            PathPatternRequestMatcher.pathPattern(HttpMethod.GET, "/swagger-ui.html"),
            PathPatternRequestMatcher.pathPattern(HttpMethod.GET, "/v3/api-docs/**"),
            PathPatternRequestMatcher.pathPattern(HttpMethod.OPTIONS, "/**")
    );

    private PublicEndpointMatcher() {
    }

    public static boolean matches(HttpServletRequest request) {
        return MATCHERS.stream().anyMatch(matcher -> matcher.matches(request));
    }

    public static RequestMatcher[] requestMatchers() {
        return MATCHERS.toArray(RequestMatcher[]::new);
    }
}
