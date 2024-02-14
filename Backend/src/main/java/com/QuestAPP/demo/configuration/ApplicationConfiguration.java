package com.QuestAPP.demo.configuration;

import com.QuestAPP.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //spring pick up this class and try to implement and inject all the beans that we will declare this application config
@RequiredArgsConstructor
public class ApplicationConfiguration {


    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return email -> userRepository.findUserByEmail(email).orElseThrow(() ->new UsernameNotFoundException("User not found"));
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        //data access object
        //responbille for the fetch user details also encode password vs vs
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider(); //kullanıcı kimlik doğrulama vs bu yapıyor
        authProvider.setUserDetailsService( userDetailsService()); //User bilgilerini alıp auth provider a atıyor
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager(); //kimlik doğrulamaya yarar
    }
}
