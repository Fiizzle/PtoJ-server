package com.example.Fiizzle.src.auth;

import com.example.Fiizzle.src.auth.model.PostLoginReq;
import com.example.Fiizzle.src.auth.model.PostRegisterReq;
import com.example.Fiizzle.src.auth.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AuthDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 회원가입
    public int insertUser(@NotNull PostRegisterReq postRegisterReq) {
        String insertUserQuery = "INSERT INTO user(email, nickname, password, status) VALUES (?, ?, ?, 1);";
        Object[] insertUserParams = new Object[]{postRegisterReq.getEmail(), postRegisterReq.getNickName(), postRegisterReq.getPassword()};
        this.jdbcTemplate.update(insertUserQuery, insertUserParams);

        String lastInsertIdQuery = "SELECT last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    // 유저 존재 여부 확인
    public int checkEmailExist(String email) {
        String checkEmailExistQuery = "SELECT exists(SELECT email from user where email = ?)";
        String checkEmailExistParams = email;

        return this.jdbcTemplate.queryForObject(checkEmailExistQuery, int.class, checkEmailExistParams);
    }

    // 로그인(유저 정보 가져오기)
    public User getUser(PostLoginReq postLoginReq) {
        String getUserQuery = "SELECT userIdx, nickname, email, password, status FROM user where email = ?";
        String getUserParams = postLoginReq.getEmail();

        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("userIdx"),
                        rs.getString("nickName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("status")
                ), getUserParams);
    }
}