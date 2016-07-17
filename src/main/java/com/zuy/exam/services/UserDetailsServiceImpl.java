package com.zuy.exam.services;

import com.zuy.exam.entities.User;
import com.zuy.exam.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = usersRepository.getUser(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        else {
            return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPasswordHash(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
        }
    }
}
