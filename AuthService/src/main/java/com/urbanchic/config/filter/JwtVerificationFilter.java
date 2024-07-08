package com.urbanchic.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Executing JwtVerificationFilter for request: {}", request.getRequestURI());
        String jwtToken  = request.getHeader("Authorization");
        String username = null;
        if (jwtToken != null){
            try {
                username = jwtUtil.getUsernameFromToken(jwtToken);
            }catch (ExpiredJwtException | MalformedJwtException | SignatureException exception){
                //exception.printStackTrace();
                // creating a map object for desired response
                ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                        .data(exception.getMessage())
                        .message("UnAuthorized access to AuthService")
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .success(false)
                        .build();

                // Converting object to json using ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                String jsonString = objectMapper.writeValueAsString(apiResponse);

                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write(jsonString);
                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                Boolean validatedtoken = jwtUtil.validateToken(jwtToken, userDetails);

                log.info("TOKEN-VALIDATION {}: ",validatedtoken);

                if (validatedtoken){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("AUTHENTICATION-OBJECT {} : AUTHENTICATION-OBJECT-ROLE {} :  ",SecurityContextHolder.getContext().getAuthentication().getName(),SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                }
            }
        }
        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        log.info("Evaluating shouldNotFilter for request: {}", request.getRequestURI());
        return request.getRequestURI().startsWith("/auth/**");
    }
}
