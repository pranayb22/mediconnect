package com.mediconnect.patientservice.config;

import com.mediconnect.patientservice.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestURI = request.getRequestURI();

        if(requestURI.startsWith("/api/auth")){
            filterChain.doFilter(request,response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
           response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing Jwt Token");
            return;
        }

try {
    final String jwt = authHeader.substring(7);
    final String userEmail = jwtService.extractEmail(jwt);

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        if (jwtService.validateToken(jwt)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userEmail, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}catch(Exception e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Jwt Token");
                return;
            }

        filterChain.doFilter(request,response);
    }
}
