package com.elcom.service.auth;

import com.elcom.auth.CustomUserDetails;
import com.elcom.model.User;
import com.elcom.repository.UserCustomizeRepository;
import com.elcom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {
    @Autowired
    private UserCustomizeRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Khong tim thay username "+ username);
        return new CustomUserDetails(user);
    }
    @Transactional
    @Override
    public UserDetails loadUserById(Integer id)
    {
        User user = userRepository.findById(id);
        if(user == null)
            throw new UsernameNotFoundException("User khoong duoc tim thay : " + id);
        return new CustomUserDetails(user);
    }
}
