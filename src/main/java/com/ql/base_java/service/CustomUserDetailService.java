package com.ql.base_java.service;

import com.ql.base_java.model.CustomUserDetails;
import com.ql.base_java.model.User;
import com.ql.base_java.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Data
@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try{
            Long.parseLong(userName);
            Long userId = Long.valueOf(userName);
            User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));
            return new CustomUserDetails(user);
        } catch (NumberFormatException e) {
            log.error("Invalid userId format: {}", userName);
            throw new UsernameNotFoundException("Invalid userId format: " + userName);
        }
    }
}

