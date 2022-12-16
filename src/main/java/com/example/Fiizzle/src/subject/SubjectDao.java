package com.example.Fiizzle.src.subject;

import com.example.Fiizzle.src.subject.model.PostSubjectReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SubjectDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 게시글 작성
    public int insertSubject(PostSubjectReq postSubjectReq){
        String insertSubjectQuery =
                "INSERT INTO subject(userId, subjectName, unit, endDate) VALUES (?, ?, ?, ?);";
        Object[] insertSubjectParams = new Object[]{postSubjectReq.getUserIdx(),postSubjectReq.getSubjectName(),postSubjectReq.getAmount(),postSubjectReq.getEndDate()};
        this.jdbcTemplate.update(insertSubjectQuery, insertSubjectParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);

    }
}
