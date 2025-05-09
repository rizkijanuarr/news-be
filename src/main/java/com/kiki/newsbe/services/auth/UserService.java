package com.kiki.newsbe.services.auth;

import com.kiki.newsbe.repositories.auth.UserRepository;
import com.kiki.newsbe.repositories.entities.auth.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity account = userRepository.login(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new User(account.getUser_email(), account.getUser_password(), Collections.emptyList());
    }
}
