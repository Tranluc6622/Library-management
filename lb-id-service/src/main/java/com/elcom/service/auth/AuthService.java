package com.elcom.service.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails loadUserById(Integer userId);
}
