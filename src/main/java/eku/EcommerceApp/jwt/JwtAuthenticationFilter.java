package eku.EcommerceApp.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Get header
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        String path = request.getRequestURI();


        if (path.startsWith("/api/weather") ||
           path.startsWith("/api/all")){

            filterChain.doFilter(request, response);
            return;

        }

        // Check format "Bearer token"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
            String role = jwtUtil.extractRole(token);  // extract role

            // Only create authority if role is present
            List<GrantedAuthority> authorities = Collections.emptyList();
            if (role != null && !role.isEmpty()) {
                authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role)); // add ROLE_ prefix
            }

            // If valid and no authentication yet
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtil.validateToken(token)) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set authentication in context
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        // Continue filter chain
        filterChain.doFilter(request, response);
    }
}

