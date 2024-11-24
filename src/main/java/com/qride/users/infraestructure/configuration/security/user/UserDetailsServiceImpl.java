package com.qride.users.infraestructure.configuration.security.user;

import com.qride.users.application.ICredentialService;
import com.qride.users.domain.models.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ICredentialService repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = repository.find(username);
        return new UserDetailsImpl(credential);
    }
}
