package com.example.Fiizzle.src.subject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetEachSubjectRes {

    private String subjectName;
    private Date endDate;
    private String Dday;

}
