package edu.yacoubi.springsecuritydemo;

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
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Authorization Bearer token
        final String authorizationHeader = request.getHeader(HEADER_STRING);
        if (authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            // skip these filter and move to the next one.
            filterChain.doFilter(request, response);
            return;
        }

        // token from the authorization request header
        // authorizationHeader.split(" ")[1].trim();
        final String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        if (!jwtUtil.validate(token)) {
            // skip these filter and move to the next one.
            filterChain.doFilter(request, response);
            return;
        }

        // Get the username from the token
        String username = jwtUtil.getUsername(token);
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.createEmptyContext().setAuthentication(authToken);
        //SecurityContextHolder.getContext().setAuthentication(authToken);

        // end, move to next filter in chain
        filterChain.doFilter(request, response);
    }
}
