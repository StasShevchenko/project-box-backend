package com.example.project_box_backend.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class SessionExtenderFilter extends OncePerRequestFilter {

    private final Clock clock = Clock.systemUTC();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            filterChain.doFilter(request, response);
        }
        for (Cookie cookie : request.getCookies()) {
            if ("SESSION".equals(cookie.getName())) {
                extendSessionCookieIfNeeded(session, response, cookie);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void extendSessionCookieIfNeeded(HttpSession session, HttpServletResponse response, Cookie cookie) {
        int maxAge = cookie.getMaxAge();
        Instant expires = this.clock.instant().plusSeconds(maxAge);
        Instant now = this.clock.instant();
        if (ChronoUnit.SECONDS.between(now, expires) < (7 * 24 * 60 * 60)) {
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
        }
    }

}