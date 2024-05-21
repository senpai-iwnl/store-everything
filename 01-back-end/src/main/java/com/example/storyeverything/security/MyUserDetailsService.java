package com.example.storyeverything.security;

import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userAccount.getRole().getName());

        return new org.springframework.security.core.userdetails.User(userAccount.getLogin(), userAccount.getPassword(), Collections.singleton(authority));
    }
}
