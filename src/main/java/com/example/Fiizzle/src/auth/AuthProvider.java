package com.example.Fiizzle.src.auth;


import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.src.auth.model.PostLoginReq;
import com.example.Fiizzle.src.auth.model.PostLoginRes;
import com.example.Fiizzle.src.auth.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.Fiizzle.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.Fiizzle.config.BaseResponseStatus.FAILED_TO_LOGIN;

@Service
public class AuthProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthDao authDao;
    @Autowired
    public AuthProvider(AuthDao authDao) {
        this.authDao = authDao;
    }
    // 이메일 존재 여부 확인
    public int checkEmailExist(String email) throws BaseException{
        try{
            return authDao.checkEmailExist(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    // 로그인
    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {
        User user;

        if (checkEmailExist(postLoginReq.getEmail()) == 1) {
            user = authDao.getUser(postLoginReq);
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }

        if (postLoginReq.getPassword().equals(user.getPassword())) {
            return new PostLoginRes(user.getUserIdx());
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }
}
