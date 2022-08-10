package com.WizardsOfTheCoast.magic.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh")){
            log.info("Authorization not needed!");
            filterChain.doFilter(request,response);
        }else{
            log.info("Authorization initiated!");
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            log.info("Authorization token: {}",request.getHeader(AUTHORIZATION));
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String encodedToken = authorizationHeader.substring("Bearer ".length());
                    String token = new String(Base64.getDecoder().decode(encodedToken));
                    log.info("Try to take Bearer token {}",token);
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes(StandardCharsets.UTF_8));
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    }
                    log.info("User roles: {}",authorities);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null, authorities);
                    log.info(String.valueOf(authenticationToken));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                } catch (Exception exception){
                    log.error("Error logging in: {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                    Map<String,String> error =new HashMap<>();
                    error.put("error_message",exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }
}
