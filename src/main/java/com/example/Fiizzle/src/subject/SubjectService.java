package com.example.Fiizzle.src.subject;

import com.example.Fiizzle.config.BaseException;
import com.example.Fiizzle.src.subject.model.PostSubjectReq;
import com.example.Fiizzle.src.subject.model.PostSubjectRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.Fiizzle.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class SubjectService {


    private final SubjectDao subjectDao;
    private final SubjectProvider subjectProvider;


    @Autowired
    public SubjectService(SubjectDao subjectDao, SubjectProvider subjectProvider) {
        this.subjectDao = subjectDao;
        this.subjectProvider = subjectProvider;

    }

    public PostSubjectRes createSubject(PostSubjectReq postsubjectReq) throws BaseException {

        try{
            int subjectIdx = subjectDao.insertSubject(postsubjectReq);

            return new PostSubjectRes(subjectIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
