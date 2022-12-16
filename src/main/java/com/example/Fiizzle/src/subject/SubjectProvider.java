package com.example.Fiizzle.src.subject;


import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.src.subject.model.GetEachSubjectRes;
import com.example.Fiizzle.src.subject.model.GetSubjectRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Fiizzle.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.Fiizzle.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

@Service
public class SubjectProvider {

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectProvider(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }


    /**
     * 전체 학습 목록 조회
     */
    public List<GetSubjectRes> findAllSubject(int userIdx) throws BaseException {
        if(userIdx==0){
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try{
            List<GetSubjectRes> getSubject = subjectDao.selectAllSubject(userIdx);
            return getSubject;
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    /**
     * 개별 학습 계획
     */
    public GetEachSubjectRes findEachSubject(int subjectIdx) throws BaseException {
//        if(subjectIdx==0){
//            throw new BaseException(USERS_EMPTY_USER_ID);
//        }
        try{
            GetEachSubjectRes getSubject = subjectDao.selectEachSubject(subjectIdx);
            return getSubject;
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
