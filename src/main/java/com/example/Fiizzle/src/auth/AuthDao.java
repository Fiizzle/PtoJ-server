package com.example.Fiizzle.src.auth;


import com.example.Fiizzle.src.auth.model.PostRegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AuthDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int insertUser(PostRegisterReq postRegisterReq) {
        String insertUserQuery = "INSERT INTO user(email, nickname, password, status) VALUES (?, ?, ?, 1);";
        Object[] insertUserParams = new Object[]{postRegisterReq.getEmail(), postRegisterReq.getNickName(), postRegisterReq.getPassword()};

        this.jdbcTemplate.update(insertUserQuery, insertUserParams);

        String lastInsertIdQuery = "SELECT last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }
}