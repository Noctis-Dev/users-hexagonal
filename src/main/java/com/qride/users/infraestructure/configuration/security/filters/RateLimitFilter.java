package com.qride.users.infraestructure.configuration.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private static final int MAX_REQUESTS_PER_MINUTE = 60;
    private final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> requestTimes = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String clientIp = request.getRemoteAddr();

        long currentTime = System.currentTimeMillis();
        requestTimes.putIfAbsent(clientIp, currentTime);
        requestCounts.putIfAbsent(clientIp, new AtomicInteger(0));

        long elapsedTime = currentTime - requestTimes.get(clientIp);
        if (elapsedTime > 60000) {
            requestTimes.put(clientIp, currentTime);
            requestCounts.get(clientIp).set(0);
        }

        if (requestCounts.get(clientIp).incrementAndGet() > MAX_REQUESTS_PER_MINUTE) {
            response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
            response.getWriter().write("Too many requests");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
