package com.nri.busmanagement.model;

//Required Imports
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nri.busmanagement.model.UserAuthentication;

// This Class Is used to pass the registered user data to the aunthentication manager
public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;
    
    public CustomUserDetails(UserAuthentication user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = Stream.of(user.getRole().split(",")).map(x->{
            return new SimpleGrantedAuthority(x);
        }).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return this.authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}