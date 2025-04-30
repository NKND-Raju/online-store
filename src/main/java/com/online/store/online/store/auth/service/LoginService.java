package com.online.store.online.store.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.store.online.store.auth.request.LoginRequest;
import com.online.store.online.store.auth.response.LoginResponse;
import com.online.store.online.store.user.entity.UserDetailsEntity;
import com.online.store.online.store.user.repo.UserDetailsRepository;

@Service
public class LoginService {

    private static final int MAX_FAILED_ATTEMPTS = 3;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();

        Optional<UserDetailsEntity> optionalUser = userDetailsRepository.findByUsernameIgnoringCase(request.getUsername());

        if (optionalUser.isPresent()) {
            UserDetailsEntity user = optionalUser.get();

            if (user.getAccountLocked().equalsIgnoreCase("Y")) {
                response.setSuccess("FAIL");
                response.setMessage("Account is locked due to multiple failed login attempts.");
                return response;
            }

            if (user.getPassword().equals(request.getPassword())) {
                response.setSuccess("SUCCESS");
                response.setMessage("Login successful");

                user.setLoginFailCount(0);  // reset on success
                user.setLastLoginTime(LocalDateTime.now());
            } else {
                int newFailCount = user.getLoginFailCount() + 1;
                user.setLoginFailCount(newFailCount);
                response.setSuccess("FAIL");

                if (newFailCount >= MAX_FAILED_ATTEMPTS) {
                    user.setAccountLocked("Y");
                    response.setMessage("Account locked due to 3 failed login attempts.");
                } else {
                    response.setMessage("Invalid username or password");
                }
            }

            userDetailsRepository.save(user);
        } else {
            response.setSuccess("FAIL");
            response.setMessage("Invalid username or password");
        }

        return response;
    }
}

