package com.example.Fiizzle.src.subject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectProvider {

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectProvider(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }
}
