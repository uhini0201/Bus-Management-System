package com.nri.busmanagement.config;
//Success handler Class for configured to handle the success url after successful login authentication

//Required imports
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication)
            throws IOException, ServletException {
    	
    	System.out.println("Entering redirection process");
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            if(authority.getAuthority().equals("ROLE_EMPLOYEE")) {
                try {
                    redirectStrategy.sendRedirect(arg0, arg1, "/user/home"); // URl redirection for user after login 
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if(authority.getAuthority().equals("ROLE_ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(arg0, arg1, "/admin/panel"); // URl redirection for admin after login
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (authority.getAuthority().equals("ROLE_CONDUCTOR")) {
            	try {
            		redirectStrategy.sendRedirect(arg0, arg1, "/conductor/showTrips"); // URl redirection for admin after login
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                throw new IllegalStateException();
            }
        });
        
    }
 
}