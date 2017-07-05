package com.ywd.service.impl;

import com.ywd.mapper.UserMapper;
import com.ywd.model.User;
import com.ywd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService UserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserService.findByName(username);
        List<GrantedAuthority> authorities = null;
        if (user != null) {
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getPower()));
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
        }
        return null;
    }
}
