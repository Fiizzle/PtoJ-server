package com.example.Fiizzle.src.user;

import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.src.user.model.GetUserInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.example.Fiizzle.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.Fiizzle.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

@Service
public class UserProvider {

    private final UserDao userDao;

    @Autowired
    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     *마이페이지 조회
     */
    public GetUserInfoRes findUserInfo(int userIdx) throws BaseException {
        if(userIdx==0){
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try{
            GetUserInfoRes getUser = userDao.selectUserInfo(userIdx);
            return getUser;
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
