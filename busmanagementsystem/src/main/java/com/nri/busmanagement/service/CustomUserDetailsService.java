package com.nri.busmanagement.service;
// Required imports
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.UserAuthentication;
import com.nri.busmanagement.repo.UserAuthenticationRepository;
import com.nri.busmanagement.model.CustomUserDetails;


// Service Layer Class for providing the service of user details to the authentication manager for authentication purposes
@Service
public class CustomUserDetailsService implements UserDetailsService{
    

    @Autowired(required = false)
    private UserAuthenticationRepository usersRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        
        Optional<UserAuthentication> user = usersRepository.findById(email);
        
        user.orElseThrow(()->
            new UsernameNotFoundException("User not Found :: "+email)
        );
        
        return new CustomUserDetails(user.get());
    }

}