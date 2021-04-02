package com.antaadama.service.impl;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.antaadama.entity.Permission;
import com.antaadama.entity.Role;
import com.antaadama.entity.User;
import com.antaadama.repository.UserRepository;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TemplateUserDetailsService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loginUser = userRepository.findByUserName(username);
        if (null == loginUser) {
            throw new UsernameNotFoundException(username);
        } else {

            List<SimpleGrantedAuthority> authorities =
                    new ArrayList<SimpleGrantedAuthority>();

            for(Role role:loginUser.getRoles()) {

                for(Permission permission:role.getPermissions()) {

                    SimpleGrantedAuthority authority =
                            new SimpleGrantedAuthority(permission.getPermissionName());
                    authorities.add(authority);
                }
            }
            return new org.springframework.security.core.userdetails.User(
                    username,
                    loginUser.getPassword(),
                    authorities
            );
        }
    }
}