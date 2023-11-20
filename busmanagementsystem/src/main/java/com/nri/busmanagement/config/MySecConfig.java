package com.nri.busmanagement.config;
//Spring Security Configuration File

//Required Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecConfig extends WebSecurityConfigurerAdapter{
    
	
	//Object of UserDetails Class in Service Package to provide authentication
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    //Object of Success Handler class in Configuration Package to Handle Success after successful authentication
    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;
    
    
    //Configure method provides the authentication manager
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.userDetailsService(userDetailsService);
    }
    
    
    //Configure method for security configuration
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers( "/img/favicon.ico").permitAll()
        .antMatchers("/index","/register","/js/**", "/css/**","/images/**")
        .permitAll()
                .antMatchers("/user/**").hasRole("EMPLOYEE")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/conductor/**").hasRole("CONDUCTOR")
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/dologin")
                .defaultSuccessUrl("/index.html", true)
                .successHandler(successHandler)
                .and().logout().logoutSuccessUrl("/loginPage")
                .permitAll();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    
    
    //Password encoder
    @Bean
    public PasswordEncoder getPasswordEncode() {
        return NoOpPasswordEncoder.getInstance();
    }
}