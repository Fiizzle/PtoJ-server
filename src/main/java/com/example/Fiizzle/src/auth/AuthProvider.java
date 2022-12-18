package com.example.Fiizzle.src.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthDao authDao;
    @Autowired
    public AuthProvider(AuthDao authDao) {
        this.authDao = authDao;
    }

}
