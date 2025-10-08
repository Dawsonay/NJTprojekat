package com.mycompany.njtprojekat.security.jwt;

import com.mycompany.njtprojekat.servis.MehanicarUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final MehanicarUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    // Lista javnih ruta koje filter MORA ignorisati!
    // Stavljamo ovde i rute koje pocinju sa /api/auth i /api/mehanicar
    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/api/auth/login",
            "/api/mehanicar" // Samo POST bi trebao biti otvoren, ali ga ignorisemo zbog sigurnosti
    );

    public JwtRequestFilter(MehanicarUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    // KLJUČNO: OPREDELJUJE KOJE RUTE TREBA IGNORISATI!
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        
        // POST /api/mehanicar - ruta za registraciju
        if (request.getMethod().equals("POST") && path.equals("/api/mehanicar")) {
            return true;
        }
        
        // SVE UNUTAR /api/auth/** - rute za autentifikaciju
        if (path.startsWith("/api/auth/")) {
            return true;
        }

        // SWAGGER/OPENAPI rute
        if (path.startsWith("/swagger-ui/") || path.startsWith("/v3/api-docs/")) {
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Ovaj deo se sada izvršava SAMO ZA ZAŠTIĆENE RUTE
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                // Greška prilikom parsiranja tokena, ali idemo dalje.
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
