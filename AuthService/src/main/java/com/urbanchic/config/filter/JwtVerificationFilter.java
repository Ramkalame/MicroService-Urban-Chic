package com.urbanchic.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urbanchic.util.ApiResponse;
import com.urbanchic.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("Processing JWT for request: {}", request.getRequestURI());

        String jwtToken = extractToken(request);
        if (isValidToken(jwtToken)) {
            try {
                String username = jwtUtil.getUsernameFromToken(jwtToken);
                validateAndAuthenticateUser(username, jwtToken, request);
            } catch (Exception e) {
                handleJwtException(response, e);
                return;
            }
        } else {
            log.warn("JWT token is missing or invalid");
            handleJwtException(response, new IllegalArgumentException("Token is missing or invalid"));
            return;
        }

        filterChain.doFilter(request, response);
    }

    //Extract Token From the Auth Header of Request
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader(AUTH_HEADER);
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            return header.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    //To Check whether token is not empty or blank string
    private boolean isValidToken(String jwtToken) {
        return jwtToken != null && !jwtToken.trim().isEmpty();
    }

    //If all is valid set the authentication object
    private void validateAndAuthenticateUser(String username, String jwtToken, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtUtil.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("User '{}' authenticated with roles: {}", username, userDetails.getAuthorities());
        }
    }

    //handling the response if any exception occurs
    private void handleJwtException(HttpServletResponse response, Exception exception) throws IOException {
        String errorMessage;
        HttpStatus status;

        if (exception instanceof ExpiredJwtException) {
            errorMessage = "Token expired";
            status = HttpStatus.UNAUTHORIZED;
        } else if (exception instanceof MalformedJwtException) {
            errorMessage = "Invalid token format";
            status = HttpStatus.UNAUTHORIZED;
        } else if (exception instanceof SignatureException) {
            errorMessage = "Invalid token signature";
            status = HttpStatus.UNAUTHORIZED;
        } else if (exception instanceof IllegalArgumentException) {
                errorMessage = exception.getMessage();
                status = HttpStatus.UNAUTHORIZED;
        } else {
            errorMessage = "Token validation error";
            status = HttpStatus.BAD_REQUEST;
        }

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(errorMessage)
                .message("Unauthorized access")
                .timestamp(LocalDateTime.now())
                .statusCode(status.value())
                .success(false)
                .build();

        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api/v1/auth/");
    }
}
