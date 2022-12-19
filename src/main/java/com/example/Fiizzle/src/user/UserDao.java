package com.example.Fiizzle.src.user;



import com.example.Fiizzle.src.user.model.GetUserInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 모든 포트폴리오 리스트 조회
     * */
    public GetUserInfoRes selectUserInfo(int userIdx) {

        String selectUserInfoQuery = "select nickname, email, done, count(userIdx)-done as ing from user where userIdx=?;";

        Object[] selectUserInfoParam = new Object[]{userIdx};

        return this.jdbcTemplate.queryForObject(selectUserInfoQuery,
                (rs, rowNum) -> new GetUserInfoRes(
                        rs.getString("nickname"),
                        rs.getString("email"),
                        rs.getInt("done"),
                        rs.getInt("ing")
                ), selectUserInfoParam);
    }
}
