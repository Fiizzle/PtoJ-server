package com.example.Fiizzle.src.auth;


import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.config.BaseResponseStatus;
import com.example.Fiizzle.src.auth.model.PostRegisterReq;
import com.example.Fiizzle.src.auth.model.PostRegisterRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Service Create, Update, Delete 의 로직 처리
@Service
public class AuthService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthDao authDao;
    private final AuthProvider authProvider;

    @Autowired
    public AuthService(AuthDao authDao, AuthProvider authProvider) {
        this.authDao = authDao;
        this.authProvider = authProvider;
    }

    // 회원가입
    public PostRegisterRes registerUser(PostRegisterReq postRegisterReq) throws BaseException {
        int userIdx = authDao.insertUser(postRegisterReq);
        return new PostRegisterRes(userIdx);
    }
}