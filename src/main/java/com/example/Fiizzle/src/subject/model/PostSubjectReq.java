package com.example.Fiizzle.src.subject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
public class PostSubjectReq {

    private int userIdx;
    private Date endDate;
    private String subjectName;
    private String amount;

}
