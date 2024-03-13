package org.sid.gestionrh.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sid.gestionrh.services.Impl.UserDetailsServiceImpl;
import org.sid.gestionrh.services.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



    public class AuthTokenFilter extends OncePerRequestFilter {
        @Autowired
        private SecurityUtils securityUtils;
        @Autowired
        private UserDetailsServiceImpl userDetailsService;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            //System.out.println(request.getHeader("Authorization"));
            try {
                if(request.getHeader("Authorization")!=null) {
                    String token = extractToken(request.getHeader("Authorization"));
                    System.out.println(securityUtils.validateToken(token));
                    System.out.println(securityUtils.getEmail(token));
                    UserDetails userDetails = userDetailsService.loadUserByUsername(securityUtils.getEmail(token));
                    System.out.println(userDetails.getAuthorities());
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }catch(AccessDeniedException exception){
                System.out.println("Access Denied");
            }
            filterChain.doFilter(request,response);
        }


        private String extractToken(String authorization) {
            if(!authorization.isEmpty() && authorization.contains("Bearer")){
                return authorization.substring(7,authorization.length());
            }
            return null;// To Do Exception
        }
    }
