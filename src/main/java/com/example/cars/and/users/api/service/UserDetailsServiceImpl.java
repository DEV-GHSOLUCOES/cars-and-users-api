package com.example.cars.and.users.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, InternalAuthenticationServiceException, AuthenticationException {
    	
    	/* Consultar no banco o usuario*/
    	User user  =  userRepository.findByUserLogin(username);
    	if (user == null) {
    		throw new UsernameNotFoundException("Invalid login or password");
			
		}
    	
    	
      return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getAuthorities());
    }

}
