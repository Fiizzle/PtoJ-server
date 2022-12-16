package com.example.Fiizzle.src.subject;

import com.example.Fiizzle.src.subject.model.GetEachSubjectRes;
import com.example.Fiizzle.src.subject.model.GetSubjectRes;
import com.example.Fiizzle.src.subject.model.PostSubjectReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class SubjectDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 학습 계획 추가
     * */
    public int insertSubject(PostSubjectReq postSubjectReq){
        String insertSubjectQuery =
                "INSERT INTO subject(userId, subjectName, unit, endDate) VALUES (?, ?, ?, ?);";
        Object[] insertSubjectParams = new Object[]{postSubjectReq.getUserIdx(),postSubjectReq.getSubjectName(),postSubjectReq.getAmount(),postSubjectReq.getEndDate()};
        this.jdbcTemplate.update(insertSubjectQuery, insertSubjectParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);

    }

    /**
     * 모든 포트폴리오 리스트 조회
     * */
    public List<GetSubjectRes> selectAllSubject(int userIdx) {

        String selectAllUserSubjectQuery = "SELECT subjectId, subjectName, endDate, DATEDIFF(date(endDate), now()) as Dday from subject where userId=?;";

        Object[] selectAllUserSubjectParam = new Object[]{userIdx};

        return this.jdbcTemplate.query(selectAllUserSubjectQuery,
                (rs, rowNum) -> new GetSubjectRes(
                        rs.getInt("subjectId"),
                        rs.getString("subjectName"),
                        rs.getDate("endDate"),
                        rs.getString("Dday")
                ), selectAllUserSubjectParam);
    }

    /**
     * 개별 학습 계획
     * */
    public GetEachSubjectRes selectEachSubject(int subjectIdx) {

        String selectEachSubjectQuery = "SELECT subjectName, endDate, DATEDIFF(date(endDate), now()) as Dday from subject where subjectId=?;";

        Object[] selectEachSubjectParam = new Object[]{subjectIdx};

        return this.jdbcTemplate.queryForObject(selectEachSubjectQuery,
                (rs, rowNum) -> new GetEachSubjectRes(
                        rs.getString("subjectName"),
                        rs.getDate("endDate"),
                        rs.getString("Dday")
                ), selectEachSubjectParam);
    }
}
